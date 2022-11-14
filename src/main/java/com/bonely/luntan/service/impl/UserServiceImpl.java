package com.bonely.luntan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonely.luntan.entity.User;
import com.bonely.luntan.mapper.UserMapper;
import com.bonely.luntan.service.UserService;
import com.bonely.luntan.util.CommunityConstant;
import com.bonely.luntan.util.CommunityUtil;
import com.bonely.luntan.util.MyMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, CommunityConstant {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain:}")
    private String domain;

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
        userMapper.updateStatus(id, status);
    }

    @Override
    public void updateHeader(int id, String headerUrl) {
        userMapper.updateHeader(id, headerUrl);
    }

    @Override
    public void updatePassword(int id, String password) {
        userMapper.updatePassword(id, password);
    }

    @Override
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();

        // 空值处理
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空!");
            return map;
        }

        // 验证账号
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "该账号已存在!");
            return map;
        }

        // 验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "该邮箱已被注册!");
            return map;
        }

        // 注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        userMapper.insert(user);
        user = userMapper.selectByName(user.getUsername());

        // 激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/community/activation/101/code
        String url = domain  + "/activation/" + user.getId() + "/" + user.getActivationCode();
        System.out.println(user.getId());
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailSender.sendMail(user.getEmail(), "激活账号", content);

        return map;
    }

    /*
    * 定义激活状态
    * 创建激活函数：
    * 从数据库查数据，如果用户状态为1，则返回重复定义
    * 用户状态为0，并且激活码相等，返回成功
    * 否则失败*/

    @Override
    public int activation(int userId, String code){
        User user = userMapper.selectById(userId);
        if(user.getStatus() == 1){
            return ACTIVATION_REPEAT;
        }
        else if(user.getActivationCode().equals(code)){
            userMapper.updateStatus(userId, 1);
            return ACTIVATION_SUCCESS;
        }
        else return ACTIVATION_FAILURE;
    }

}
