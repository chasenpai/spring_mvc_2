package hello.itemservice.web.validation;


import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    /**
     * @ModelAttribute vs @RequestBody
     * - HttpMessageConverter 는 @ModelAttribute 와 다르게 각각의 필드 단위로 적용되는게 아닌 전체 객체 단위로
     * - 적용되기 때문에 컨버터의 작동이 성공해서 ItemSaveForm 객체를 만들어야 @Validated 가 작동한다
     * - @ModelAttribute 는 필드 단위로 정교하게 바인딩이 적용되기 때문에 특정 필드가 바인딩 되지 않아도
     * - 나머지 필드는 정상 바인딩 되고 검증도 적용할 수 있다
     * - @RequestBody 는 HttpMessageConverter 단계에서 JSON 데이터를 객체로 변경하지 못하면 이후 단계 자체가
     * - 진행되지 않고 예외가 발생하기 때문에 컨트롤러도 호출되지 않고 검증도 적용되지 않는다
     */
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated  ItemSaveForm form, BindingResult bindingResult){
        log.info("API 컨트롤러 호출");

        /**
         * API 는 3가지 경우를 나누어 생각해야 한다
         * 1. 성공 요청 : 성공
         * 2. 실패 요청 : JSON 을 객체로 생성하는 것 자체가 실패
         * 3. 검증 오류 요청 : JSON 을 객체로 생성하는 것은 성공했으나 검증에서 실패
         */
        if(bindingResult.hasErrors()){
            log.info("검증 오류 발생 errors = {}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행");
        return form;
    }

}
