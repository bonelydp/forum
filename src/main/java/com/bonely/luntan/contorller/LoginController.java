package com.bonely.luntan.contorller;

import com.bonely.luntan.entity.User;
import com.bonely.luntan.service.UserService;
import com.bonely.luntan.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.ws.soap.Addressing;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String registerPage(){
        return "site/register";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "site/login";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(Model model, User user){
        Map<String, Object> map = userService.register(user);
        if(map == null || map.isEmpty()){
            model.addAttribute("msg", "我们已经向您的邮箱发送了激活邮件，请尽快激活！");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        }
        else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/site/register";
        }
    }

    /*
    * 调用函数，查询当前用户的结果*/
    @RequestMapping(path = "/activation/{userId}/{code}", method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code){
        int status = userService.activation(userId, code);
        if(status == ACTIVATION_FAILURE){
            model.addAttribute("msg", "激活码不正确！");
            model.addAttribute("target", "/index");
        }
        else if(status == ACTIVATION_REPEAT){
            model.addAttribute("msg", "您已激活，请勿重复操作！");
            model.addAttribute("target", "/index");
        }
        else {
            model.addAttribute("msg", "激活成功！");
            model.addAttribute("target", "/site/login");
        }
        return  "/site/operate-result";
    }

}
