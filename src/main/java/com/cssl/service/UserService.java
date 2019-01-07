package com.cssl.service;

import com.cssl.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface UserService {

    //用户登录操作
    public User login(User user);
    //判断是否同名
    public int Cun(String username);
    //用户注册操作
    public int  regist(User user);
    //用户登录后进行的查询
    public List<Map<String,Object>> selectall(String title);
}
