package com.ifzer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by nelson on 2018-04-19.
 */
@EnableTransactionManagement
@SpringBootApplication

public class Application {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        LOGGER.info("starting application");
        SpringApplication.run(Application.class);
        LOGGER.info("started application");
    }
}
