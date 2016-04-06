package com.itibo.grob.webapp.model;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
