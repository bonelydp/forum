package com.bonely.luntan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonely.luntan.entity.User;
import com.bonely.luntan.mapper.UserMapper;
import com.bonely.luntan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public void updateStatus(int id, int status) {
        return ;
    }

    @Override
    public void updateHeader(int id, String headerUrl) {
        return ;
    }

    @Override
    public void updatePassword(int id, String password) {
        return ;
    }
}
