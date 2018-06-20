package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan("com")
@EnableWebMvc
public class Config implements WebMvcConfigurer {


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        System.out.println("============configureViewResolversIniter==========");
        registry.jsp().prefix("/WEB-INF/jsp/");
        registry.jsp().suffix(".jsp");
    }

}
