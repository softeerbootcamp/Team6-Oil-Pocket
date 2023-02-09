package org.example.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FileUploadServiceTest {
    @Autowired
    FileUploadService fileUploadService;

    @Test
    @DisplayName("파일 따옴표 제거")
    void test1(){
        FileUploadService fileUploadService = null;
        String line = "\"하이\"";
        fileUploadService.removeDoubleQuotationMarks(line);
    }

    @Test
    @DisplayName("fileopen 테스트")
    void test2() {
        fileUploadService.fileOpen();
    }
}