package com.bonely.luntan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bonely.luntan.entity.LoginTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginTicketMapper extends BaseMapper<LoginTicket> {

    @Select("select * from login_ticket where ticket = '${ticket}'")
    LoginTicket selectByTicket(String ticket);

    @Select("update login_ticket set status = ${status} where ticket = ${ticket}")
    int updateStatus(String ticket, int status);
}
