package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {

    /**
     * 사실 이 컨버터는 개발자가 직접 컨버팅 하는 것과 큰 차이가 없다
     * 편리하게 변환 기능을 제공하는 무언가가 필요 > ConversionService
     */
    @Override
    public Integer convert(String source) {
        log.info("convert source = {}", source);
        return Integer.valueOf(source);
    }

}
