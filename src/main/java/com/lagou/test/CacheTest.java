package com.lagou.test;

import com.lagou.mapper.IOrderMapper;
import com.lagou.mapper.IUserMapper;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheTest {

    private IUserMapper userMapper;
    private SqlSession sqlSession;

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void firstLevelCache() {
        // 第一次查询id为1的用户
        User user1 = userMapper.findUserById(1);

        // 更新数据
        User user = new User();
        user.setId(1);
        user.setUsername("jack");
        userMapper.updateUser(user);

        // 第二次查询id为1的用户
        User user2 = userMapper.findUserById(1);

        System.out.println(user1 == user2);
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void secondLevelCache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);
        IUserMapper userMapper3 = sqlSession3.getMapper(IUserMapper.class);

        User user1 = userMapper1.findUserById(1);
        sqlSession1.close(); // 清空一级缓存

        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        userMapper3.updateUser(user);
        sqlSession3.commit();

        User user2 = userMapper2.findUserById(1);

        System.out.println(user1 == user2);
    }
}
