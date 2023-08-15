package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * ExceptionHandler 를 사용하면 예외가 서블릿 컨테이너까지 전달되지 않고 스프링 MVC 에서 끝이난다
     * 결과적으로 WAS 입장에서는 정상 처리가 된 것이고, 이렇게 예외를 이곳에서 모두 처리할 수 있는게 핵심이다
     * - 서블릿 컨테이너까지 예외가 올라가면 복잡하고 지저분하게 추가 프로세스가 실행되지만
     * - ExceptionHandler 를 사용하면 예외처리가 상당히 깔끔해진다
     * - 하지만 직접 ExceptionHandler 를 구현하려고 하니 상당히 복잡하다
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try{

            if(ex instanceof UserException){
                log.info("UserException resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if(acceptHeader.equals("application/json")){
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("ex", ex.getClass());
                    errorResult.put("message", ex.getMessage());

                    String result = objectMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result);
                    return new ModelAndView();
                }else{
                    return new ModelAndView("error/500");
                }
            }

        }catch (IOException e){
            log.error("resolver ex", e);
            e.printStackTrace();
        }

        return null;
    }

}
