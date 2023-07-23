package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final String LOG_ID = "logId";

    /**
     * 스프링 인터셉터 흐름 - HTTP 요청 > WAS > 필터 > 서블릿 > 스프링 인터셉터 > 컨트롤러
     * - 스프링 인터셉터는 디스패처 서블릿과 컨트롤러 사이에서 컨트롤러 직전에 호출된다
     */
    @Override //컨트롤러 호출 전에 호출
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);

        //@RequestMapping - HandlerMethod
        //정적 리소스 - ResourceHttpRequestHandler
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler; //호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다
        }

        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
        return true; //true 면 다음으로 진행, false 면 나머지 인터셉터는 물론이고 컨트롤러도 호출하지 않음
    }

    //컨트롤러 호출 후에 호출
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }

    //뷰가 렌더링 된 이후에 호출
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);
        log.info("RESPONSE [{}][{}][{}]", logId, requestURI, handler);

        //afterCompletion 은 예외가 발생해도 호출된다
        if(ex != null){
            log.error("afterCompletion error !!", ex);
        }
    }

}
