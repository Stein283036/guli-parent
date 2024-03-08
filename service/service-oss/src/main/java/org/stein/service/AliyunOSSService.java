package org.stein.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author stein
 * @date 2024/3/8
 */
public interface AliyunOSSService {

    String uploadFile(MultipartFile file);
}
