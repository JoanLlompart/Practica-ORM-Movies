package com.esliceu.movies.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterruptor sessionInterruptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterruptor)
                .addPathPatterns("/register","adminArea/**","/movieSearch/movieActor");
               // .addPathPatterns("/register","/adminArea/**");
    }
}
