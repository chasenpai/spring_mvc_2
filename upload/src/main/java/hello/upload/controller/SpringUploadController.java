package hello.upload.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/spring")
public class SpringUploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    /**
     * 스프링은 MultipartFile 인터페이스로 멀티파트 파일을 매우 편리하게 지원한다
     * - 업로드하는 HTML Form 의 name 에 맞춰 @RequestParam 을 적용하면 된다. @ModelAttribute 도 마찬가지
     */
    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName, @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

        log.info("request = {}", request);
        log.info("itemName = {}", itemName);
        log.info("multipartFile = {}", file);

        if(!file.isEmpty()){
            String fullPath = fileDir + file.getOriginalFilename(); //업로드 파일명
            log.info("파일 저장 fullPath ={}", fullPath);
            file.transferTo(new File(fullPath)); //파일 저장
        }

        return "upload-form";
    }

}
