package hello.exception.exhandler.advice;

import hello.exception.api.ApiExceptionV3Controller;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@RestControllerAdvice(annotations = RestController.class) //특정 어노테이션이 있는 컨트롤러 지정
//@RestControllerAdvice(basePackages = "hello.exception.api") //특정 패키지 지정
@RestControllerAdvice(assignableTypes = {ApiExceptionV3Controller.class}) //특정 컨트롤러 클래스 지정
public class ExControllerAdvice {

    /**
     * @ControllerAdvice
     * - 대상으로 지정한 여러 컨트롤러에 @ExceptionHandler, @InitBinder 기능을 부여해준다
     * - 대상을 지정하지 않으면 모든 컨트롤러에 적용된다
     * - @RestControllerAdvice 와 차이는 @RestController 와 같다
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalHandler(IllegalArgumentException e){
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
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

}
