package com.showTime.common.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileOperation {
    public static String download(String realPath,MultipartFile file) throws IOException {
        File downloadFile;
        int index=file.getOriginalFilename().lastIndexOf(".");
        String extendName= file.getOriginalFilename().substring(index);
        String full = realPath+extendName;
        downloadFile = new File(full);
        file.transferTo(downloadFile);
        return extendName;
    }
}
