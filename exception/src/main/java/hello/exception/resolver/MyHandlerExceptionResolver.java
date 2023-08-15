package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    /**
     * 스프링 MVC 는 핸들러 밖으로 예외가 던져진 경우 예외를 해결하고 동작을 새로 정의할 수 있는 방법을 제공한다
     * - 예외를 처리해서 정상 흐름 처럼 변경하는 것이 목적이다
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try{

            if(ex instanceof IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");
                //서블릿에서 상태 코드에 따른 오류를 처리하도록 위임
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                //빈 ModelAndView 를 반환하면 정상 흐름으로 서블릿이 리턴. View, Model 정보를 담으면 뷰를 렌더링
                return new ModelAndView();
            }

        }catch (IOException e){
            log.error("resolver ex,", e);
            ex.printStackTrace();
        }

        return null; //null 반환 시 다음 ExceptionHandler 를 찾는다. 없으면 기존에 발생한 예외를 서블릿 밖으로 던진다
    }

}
