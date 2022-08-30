package com.lagou.mapper;

import com.lagou.pojo.Role;
import com.lagou.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class) // 开启二级缓存
public interface IUserMapper {

    // 查询所有用户信息，同时查询出每个用户关联的订单信息
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "orderList", column = "id", javaType = List.class,
                many = @Many(select = "com.lagou.mapper.IOrderMapper.findOrderByUid")
            )
    })
    public List<User> findAll();

    // 查询所有用户信息, 同时查出每个用户关联的角色信息
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "roleList", column = "id", javaType = List.class,
                many = @Many(select = "com.lagou.mapper.IRoleMapper.findRoleByUid")
            )
    })
    public List<User> findAllUserAndRole();

    // 添加用户
    @Insert("insert into user values(#{id}, #{username}, #{password}, #{birthday})")
    public void addUser(User user);

    // 修改用户
    @Update("update user set username = #{username} where id = #{id}")
    public void updateUser(User user);

    // 查询用户
    @Select("select * from user")
    public List<User> selectUser();

    // 删除用户
    @Delete("delete from user where id = #{id}")
    public void deleteUser(Integer id);

    // 根据id查询用户
    @Options(useCache = true) // 禁用二级缓存
    @Select("select * from user where id = #{id}")
    public User findUserById(Integer id);

}
