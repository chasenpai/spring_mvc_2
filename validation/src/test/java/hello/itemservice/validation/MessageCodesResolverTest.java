package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject(){

        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        /**
         * 기본 메세지 생성 규칙 - 객체 오류
         * 예) 오류 코드 : required, 객체 이름 : item
         * 1. required.item
         * 2. required
         */
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField(){

        String[] messageCodes = codesResolver.resolveMessageCodes(
                "required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        /**
         * 기본 메세지 생성 규칙 - 필드 오류
         * 예) 오류 코드 : required, 객체 이름 : item, 필드명 : itemName, 필드 타입 : String
         * 1. required.item.itemName
         * 2. required.itemName
         * 3. required.java.lang.String
         * 4. required
         */
        assertThat(messageCodes).containsExactly("required.item.itemName",
                "required.itemName", "required.java.lang.String", "required");
    }

}
