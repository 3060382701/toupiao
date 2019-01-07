package com.cssl.jieq;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class MyMvcConfig implements WebMvcConfigurer {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            MyInterceptor sss = new MyInterceptor();
            registry.addInterceptor(sss).addPathPatterns("/*").excludePathPatterns("/static/**","/login.html","/logining");
        }
    }

