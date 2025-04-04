package com.example.schedule.config;

import com.example.schedule.filter.ScheduleFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean scheduleFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new ScheduleFilter());

        filterRegistrationBean.setOrder(1);

        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

}
