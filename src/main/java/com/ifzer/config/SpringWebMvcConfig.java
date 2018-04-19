package com.ifzer.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by nelson on 2018-04-19.
 */
@Configuration
@ControllerAdvice("com.ifzer.**.*Controller")
public class SpringWebMvcConfig {
}
