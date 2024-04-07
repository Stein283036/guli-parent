package org.stein.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author stein
 * @date 2024/3/21
 */
public interface AliyunVodService {
    String uploadVideo(MultipartFile file);

    void deleteVideo(String videoSourceId);
}
