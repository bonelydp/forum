package com.bonely.luntan;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest

public class LoggerTest {
    private static final Logger loger = LoggerFactory.getLogger(LoggerTest.class);


    @Test
    public void testLogger(){
        loger.debug("debug");
        loger.info("info");
        loger.error("error");
    }
}
