package hello.exception.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class ErrorPageController {

    /**
     * 예외 발생과 오류 페이지 요청 흐름
     * 1. 예외가 발생해서 WAS 까지 전파된다
     * 2. WAS 는 오류 페이지 경로를 찾아서 내부에서 오류 페이지를 호출한다
     */
    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        log.info("errorPage 404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        log.info("errorPage 500");
        return "error-page/500";
    }

    /**
     * produces - Http Header 의 Accept 값이 application/json 일 때 호출
     */
    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> errorPage500Api(HttpServletRequest request, HttpServletResponse response) {
        log.info("API errorPage 500");
        Map<String, Object> result = new HashMap<>();
        Exception ex = (Exception) request.getAttribute("jakarta.servlet.error.exception");
        result.put("status", request.getAttribute("jakarta.servlet.error.status_code"));
        result.put("message", ex.getMessage());
        Integer statusCode = (Integer)
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return new ResponseEntity(result, HttpStatus.valueOf(statusCode));
    }

}
