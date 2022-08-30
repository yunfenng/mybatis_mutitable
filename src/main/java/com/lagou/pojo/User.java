package com.lagou.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
public class User implements Serializable {

    @Id // 对应的主键id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键的生成策略
    private Integer id;
    private String username;
    private String password;
    private String birthday;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    // // 该用户下具有的订单信息
    // private List<Order> orderList = new ArrayList<>();
    //
    // // 表示用户关联的信息
    // private List<Role> roleList = new ArrayList<>();
    //
    // @Override
    // public String toString() {
    //     return "User{" +
    //             "id=" + id +
    //             ", username='" + username + '\'' +
    //             ", password='" + password + '\'' +
    //             ", birthday='" + birthday + '\'' +
    //             ", roleList=" + roleList +
    //             '}';
    // }
    //
    // public List<Role> getRoleList() {
    //     return roleList;
    // }
    //
    // public void setRoleList(List<Role> roleList) {
    //     this.roleList = roleList;
    // }
    //
    // public List<Order> getOrderList() {
    //     return orderList;
    // }
    //
    // public void setOrderList(List<Order> orderList) {
    //     this.orderList = orderList;
    // }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
