package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResponseStatusExceptionResolver
 * - 예외에 따라서 HTTP 상태 코드를 지정해주는 역할을 한다
 * - 해당 예외가 컨트롤러 밖으로 넘어가면 해당 어노테이션을 확인해서 오류 코드를 변경하고 메세지도 담는다
 * - 가장 쉬운 방법이지만 개발자가 직접 변경할 수 없는 예외에는 적용할 수 없다
 * - 그럴 땐 ResponseStatusException 예외를 사용하면 된다
 */
//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류")
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad")
public class BadRequestException extends RuntimeException {



}
