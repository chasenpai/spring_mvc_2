package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 스프링은 내부에서 ConversionService 를 제공한다. WebMvcConfigurer 가 제공하는
     * addFormatters() 를 사용해서 추가하고 싶은 컨버터를 등록하면 된다
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //컨버터가 우선순위가 높아서 주석
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        //포맷터 추가
//        registry.addFormatter(new MyNumberFormatter());
    }

}
