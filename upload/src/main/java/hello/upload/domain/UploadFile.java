package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; //클라이언트 업로드 파일명
    private String storeFileName; //서버에서 관리할 파일명

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

}