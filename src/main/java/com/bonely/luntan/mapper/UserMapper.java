package com.bonely.luntan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bonely.luntan.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where id = ${id}")
    User selectById(int id);

    @Select("select * from user where username = '${username}'")
    User selectByName(String username);

    @Select("select * from user where email = '${email}'")
    User selectByEmail(String email);

    //mybstis-plus提供的更新方法是根据id一次性更新整体
    //后面根据情况选择


    @Select("update user set status = ${status} where id = ${id}")
    void updateStatus(int id, int status);

    @Select("update user set headerUrl = ${headerUrl} where id = ${id}")
    void updateHeader(int id, String headerUrl);

    @Select("update user set password = ${password} where id = ${password}")
    void updatePassword(int id, String password);

}
