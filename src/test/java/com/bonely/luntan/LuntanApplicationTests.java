package com.bonely.luntan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bonely.luntan.entity.DiscussPost;
import com.bonely.luntan.entity.User;
import com.bonely.luntan.mapper.UserMapper;
import com.bonely.luntan.service.DiscussPostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LuntanApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostService discussPostService;


    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        LambdaQueryWrapper<DiscussPost> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DiscussPost::getUserId, 149);
        List<DiscussPost> discussPosts = discussPostService.list(lambdaQueryWrapper);
        System.out.println(discussPosts);
        System.out.println(discussPosts.size());

    }

}
