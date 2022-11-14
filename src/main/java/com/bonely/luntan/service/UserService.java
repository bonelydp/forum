package com.bonely.luntan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bonely.luntan.entity.User;

public interface UserService extends IService<User> {
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    void updateStatus(int id, int status);

    void updateHeader(int id, String headerUrl);

    void updatePassword(int id, String password);

}
