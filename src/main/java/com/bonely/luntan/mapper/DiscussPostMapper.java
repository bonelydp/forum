package com.bonely.luntan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bonely.luntan.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DiscussPostMapper extends BaseMapper<DiscussPost> {

    //根据用户id查询帖子集合和数量，目前思考可以使用lamd查询
    //遗留问题：需要自己分页，后续解决

    //目前没有思考出来怎么使用mp，采用sql语句
    @Select("select * from discuss_post where user_id = ${userId} and status != 2 limit ${page} ,${pageSize}")
    public List<DiscussPost> selectDiscussPosts(int userId, int page, int pageSize);

    @Select("select count(id) from discuss_post where user_id = ${userId} and status != 2")
    public int selectDiscussPostRows(int userId);
}
