package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadServiceTest {
    @Test
    @DisplayName("파일 따옴표 제거")
    void test1(){
        FileUploadService fileUploadService = null;
        String line = "\"하이\"";
        fileUploadService = new FileUploadService();
        fileUploadService.removeDoubleQuotationMarks(line);
    }

    @Test
    @DisplayName("fileopen")
    void test2() {
        FileUploadService fileUploadService = null;
        fileUploadService = new FileUploadService();
        fileUploadService.fileOpen();
    }
}