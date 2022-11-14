package com.bonely.luntan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bonely.luntan.entity.DiscussPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscussPostService extends IService<DiscussPost> {
    List<DiscussPost> selectDiscussPosts(int userId, int page, int pageSize);

    int selectDiscussPostRows(int userId);

}
