package cn.luxinhuo.concurrent_coding.stage1.threadlocal;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//配置自定义过滤器，使得过滤器生效
@Component
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<HttpFilter> httpFilter(){
        FilterRegistrationBean<HttpFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpFilter());
        registrationBean.addUrlPatterns("/filter/*");

        return registrationBean;
    }
}
