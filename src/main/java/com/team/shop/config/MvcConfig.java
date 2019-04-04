package com.team.shop.config;


import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.team.shop.interceptor.AuthenticationInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@Component
@PropertySource("classpath:uploadFiles.properties")
@ConfigurationProperties(prefix = "uploadfiles")
public class MvcConfig implements WebMvcConfigurer {

    @Getter
    @Setter
    private String realPath;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/images/**");
    }

    /**
     *  token 验证拦截器
     * @return
     */
    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         *  图片资源映射
         */
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + realPath);
    }
}
