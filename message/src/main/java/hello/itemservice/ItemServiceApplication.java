package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	/**
	 * 스프링 메세지 소스 설정
	 * - 스프링 부트는 자동으로 해준다
	 * - ResourceBundleMessageSource 를 스프링 빈으로 등록
	 */
//	@Bean
//	public MessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new
//				ResourceBundleMessageSource();
//		messageSource.setBasenames("messages", "errors"); //설정 파일의 이름 지정
//		messageSource.setDefaultEncoding("utf-8"); //인코딩 정보 지정
//		return messageSource;
//	}

	/**
	 * MessageSource 를 스프링 빈으로 등록하지 않고 스프링 부트와 관련된 별도의 설정을 하지 않으면
	 * message 라는 이름이 기본으로 등록된다. message_en.properties 이런 식으로 파일만 등록하면 자동으로 인식됌
	 */

}
