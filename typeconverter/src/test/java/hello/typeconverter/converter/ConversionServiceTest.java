package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class ConversionServiceTest {

    /**
     * 스프링은 개별 컨버터를 모아두고 그것들을 묶어서 편리하게 사용할 수 있도록
     * 컨버전서비스라는 기능을 제공한다
     *
     * 등록과 사용의 분리
     * - 컨버터를 등록할 땐 타입 컨버터를 명확하게 알아야 한다
     * - 반면에 컨터러를 사용하는 입장에서는 타입 컨터버를 전혀 몰라도 된다
     * - 타입 변환을 원하는 사용자는 컨버전 서비스 인터페이스만 의존하고
     * - 컨버전 서비스를 등록하는 부분과 사용하는 부분을 분리하고 의존관계 주입을 해야함
     *
     * DefaultConversionService
     * - DefaultConversionService 는 두 인터페이스를 구현한다
     * - ConversionService 는 컨버터 사용에 초점
     * - ConverterRegistry 는 컨버터 등록에 초점
     * - 컨버터를 사용하는 클라이언트와 등록하는 클라이언트의 관심자를 명확하게 분리 가능
     *
     * 스프링은 내부에서 ConversionService 를 사용해서 타입을 변화한다 예)@RequestParam
     */
    @Test
    void conversionService(){

        //등록
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //사용
        Integer result1 = conversionService.convert("10", Integer.class);
        String result2 = conversionService.convert(10, String.class);
        IpPort result3 = conversionService.convert("127.0.0.1:8080", IpPort.class);
        String result4 = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);

        //검증
        assertThat(result1).isEqualTo(10);
        assertThat(result2).isEqualTo("10");
        assertThat(result3).isEqualTo(new IpPort("127.0.0.1", 8080));
        assertThat(result4).isEqualTo("127.0.0.1:8080");

    }

}
