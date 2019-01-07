package com.cssl.service.impl;

import com.cssl.dao.Userdao;
import com.cssl.pojo.User;
import com.cssl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Userdao userdao;
    @Override
    public User login(User user)
    {
        return userdao.login(user);
    }
    @Override
    public int Cun(String username)

    {
        return userdao.Cun(username);
    }
    @Override
    public int  regist(User user)
    {

        return userdao.regist(user);
    }
    @Override
    public List<Map<String,Object>> selectall(String title)

    {
        return userdao.selectall(title);
    }
}
