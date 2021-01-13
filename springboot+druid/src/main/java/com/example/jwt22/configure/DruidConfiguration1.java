package com.example.jwt22.configure;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DruidConfiguration1 {
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean sb = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        sb.addInitParameter("loginUsername", "admin");
        sb.addInitParameter("loginPassword", "admin");
        sb.addInitParameter("resetEnable", "true");
        return sb;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        System.out.println("过滤中");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}