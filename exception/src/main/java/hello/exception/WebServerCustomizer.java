package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    /**
     * 스프링 부트는 예외 처리 페이지를 간편하게 구현하기 위한 BasicErrorController 를 제공한다
     * BasicErrorController 가 제공하는 룰과 우선순위에 따라서 오류 페이지를 등록하면 된다
     * 오류가 발생하면 오류 페이지로 /error 를 기본으로 요청한다
     *
     * 뷰 선택 우선순위
     * 1. 뷰 템플릿 - resources/templates/error/500.html
     * 2. 정적 리소스 - resources/static/error/400.html
     * 3. 적용 대상이 없을 때 뷰이름 error - resources/templates/error.html
     */

    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");

        //WAS 는 해당 예외를 처리하는 오류 페이지 정보를 확인하고 /error-page/500 을 다시 요청한다
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");

        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);
    }

}
