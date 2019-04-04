package com.team.shop.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Component
@PropertySource("classpath:uploadFiles.properties")
@ConfigurationProperties(prefix = "uploadfiles")
public class UploadFileService {

    @Getter
    @Setter
    private String realPath;

    /**
     *  指定名字保存文件
     * @param file 源文件
     * @param name 新文件名称
     * @throws IOException
     */
    public void saveFileAsName(MultipartFile file,String name) throws IOException {
        String fileFinalName = realPath + name;
        file.transferTo(new File(fileFinalName));
    }
}
