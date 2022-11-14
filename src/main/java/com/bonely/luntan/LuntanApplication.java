package com.bonely.luntan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@Slf4j
@ServletComponentScan
public class LuntanApplication {

    public static void main(String[] args) {
        log.info("项目启动成功！！！！！");
        SpringApplication.run(LuntanApplication.class, args);
    }

}
