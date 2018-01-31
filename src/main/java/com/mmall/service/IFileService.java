package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ausu on 2018/1/30.
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
