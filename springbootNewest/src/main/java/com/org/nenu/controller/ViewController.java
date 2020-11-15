package com.org.nenu.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       //TODO 留作日后展示型页面跳转页面
        registry.addViewController("/greetings").setViewName("ajax_test/ajax_exp");
        registry.addViewController("/student_home_page").setViewName("student_home_page");
        registry.addViewController("/teacher_home_page").setViewName("teacher_home_page");
        registry.addViewController("/administrator_home_page").setViewName("administrator_home_page");
    }
}
