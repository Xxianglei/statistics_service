package com.xianglei.statistics_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.xianglei.statistics_service.mapper")
public class CountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountServiceApplication.class, args);
    }
    /* * 设置匹配*.json后缀请求
     * @param dispatcherServlet
     * @return-该设置严格指定匹配后缀*.do或.action，但有风险 可能不生效
     *
     **//*
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
        servletServletRegistrationBean.addUrlMappings("*.json");
        return servletServletRegistrationBean;
    }*/
}
