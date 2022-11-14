package com.bonely.luntan.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonely.luntan.entity.DiscussPost;
import com.bonely.luntan.mapper.DiscussPostMapper;
import com.bonely.luntan.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscussPostServiceImpl extends ServiceImpl<DiscussPostMapper,DiscussPost> implements DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Override
    public List<DiscussPost> selectDiscussPosts(int userId, int page, int PageSize) {

        return discussPostMapper.selectDiscussPosts(userId, page, PageSize);
    }

    @Override
    public int selectDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
