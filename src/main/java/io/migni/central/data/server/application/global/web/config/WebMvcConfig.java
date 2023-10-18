package io.migni.central.data.server.application.global.web.config;

import io.migni.central.data.server.application.global.web.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        final FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>(new LogFilter());

        registrationBean.addUrlPatterns("/sensing/*");

        return registrationBean;
    }

}
