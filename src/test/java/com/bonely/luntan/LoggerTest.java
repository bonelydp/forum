package com.bonely.luntan;

import com.bonely.luntan.util.MyMailSender;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@SpringBootTest

public class LoggerTest {
    private static final Logger loger = LoggerFactory.getLogger(LoggerTest.class);

    @Autowired
    private MyMailSender myMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    @Test
    public void testLogger(){
        loger.debug("debug");
        loger.info("info");
        loger.error("error");
    }

    @Test
    public void sendMail(){
        myMailSender.sendMail("1517227060@qq.com", "test", "test");
    }

    @Test
    public void senfMailHtml(){
        Context context = new Context();
        context.setVariable("username","sunday");

        String content  = templateEngine.process("/mail/demo",context);
        System.out.println(content);
        myMailSender.sendMail("1517227060@qq.com", "test", content);
    }
}
