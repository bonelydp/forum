package com.bonely.luntan.contorller;

import com.bonely.luntan.entity.DiscussPost;
import com.bonely.luntan.entity.Page;
import com.bonely.luntan.entity.User;
import com.bonely.luntan.service.DiscussPostService;
import com.bonely.luntan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){

        page.setRows(discussPostService.selectDiscussPostRows(111));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.selectDiscussPosts(111,page.getOffset(),page.getLimit());
        List<Map<String,Object>> discussPosts = new ArrayList<>();

        if(list != null){
            for(DiscussPost post : list){
                Map<String,Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.selectById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);

        return "/index";
    }
}
