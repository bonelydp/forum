package com.bonely.luntan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bonely.luntan.entity.LoginTicket;
import com.bonely.luntan.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {
    User selectById(int id);

    public Map<String, Object> register(User user);

    public int activation(int userID, String code);

    public Map<String, Object> login(String username, String password, long expired);

    public void logout(String ticket);

    public LoginTicket findLoginTicket(String ticket);

    public void updateHeader(int userId, String headerUrl);
}
