package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    /**
     * @ExceptionHandler
     * - 스프링은 API 예외 처리 문제를 해결하기 위해 해당 어노테이션을 제공한다
     * - ExceptionHandlerExceptionResolver 를 기본으로 제공하고 ExceptionResolver 중 우선 순위도 가장 높다
     * - 해당 컨트롤러에서 예외가 발생하면 이 메서드가 호출된다
     * - 지정한 예외 또는 그 예외의 자식 클래스는 모두 잡을 수 있다
     * - 스프링은 둘 중 더 자세한 것이 우선권을 가지기 때문에 자식 예외 처리가 호출된다
     * - 마치 스프링의 컨트롤러의 파라미터 응답처럼 다양한 파라미터와 응답을 지정할 수 있다
     * - ModelAndView 를 반환해서 오류 페이지를 보여주는데 사용할 수 있다
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalHandler(IllegalArgumentException e){
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    /**
     * 실행 흐름
     * - 컨트롤러가 호출한 결과 UserException 예외가 컨트롤러 밖으로 던져진다
     * - 가장 우선순위가 높은 ExceptionHandlerExceptionResolver 가 실행된다
     * - 해당 컨트롤러에 해당 예외를 처리할 수 있는 @ExceptionHandler 를 찾고 실행한다
     */
    @ExceptionHandler //예외 생략
    public ResponseEntity<ErrorResult> userHandler(UserException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }
        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }

}
