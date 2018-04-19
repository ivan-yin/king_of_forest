package com.ifzer.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nelson on 2018-04-19.
 */
@Configuration
//@MapperScan
public class MyBatisPlusConfig {

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor pi = new PerformanceInterceptor();
        pi.setFormat(false);
        pi.setMaxTime(0);
        pi.setWriteInLog(true);
        return pi;
    }

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor pi = new PaginationInterceptor();
        pi.setLocalPage(true);
        return pi;
    }
}
