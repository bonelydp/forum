package com.bonely.luntan.entity;

import lombok.Data;
import java.util.Date;


@Data
public class DiscussPost {
    private int id;
    private int userId;
    private String title;
    private String Content;
    private int type;
    private int status;
    private Date createTime;
    private int commentCount;
    private double score;
}
