package hello.login.web;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean =  new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter()); //등록할 필터 지정
        filterFilterRegistrationBean.setOrder(1); //순서 지정 - 필터는 체인으로 동작한다. 낮을 수록 먼저 동작
        filterFilterRegistrationBean.addUrlPatterns("/*"); //필터를 적용할 URL 패턴 지정 - 한번에 여러 패턴 가능
        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean =  new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }

}
