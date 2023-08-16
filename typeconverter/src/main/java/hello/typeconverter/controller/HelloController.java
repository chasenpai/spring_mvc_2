package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data"); //문자 타입 조회
        Integer intValue = Integer.valueOf(data); //숫자 타입으로 변경
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        //스프링이 중간에서 타입을 변환해줌 @ModelAttribute, @PathVariable 도 마찬가지
        System.out.println("data = " + data);
        //컨버터를 추가하면 추가한 컨버터가 기본 컨버터 보다 높은 우선순위를 가진다
        return "ok";
    }

    /**
     * - @RequestParam 을 처리하는 ArgumentResolver 인 RequestParamMethodArgumentResolver 에서
     * - ConversionService 를 사용해서 타입을 변환한다
     */
    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }

}
