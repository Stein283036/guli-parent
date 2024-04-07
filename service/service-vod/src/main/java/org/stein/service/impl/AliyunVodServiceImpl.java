package org.stein.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.stein.config.AliyunVodConfiguration;
import org.stein.handler.exception.GuliException;
import org.stein.result.StatusCode;
import org.stein.service.AliyunVodService;

/**
 * @author stein
 * @date 2024/3/21
 */
@Service
public class AliyunVodServiceImpl implements AliyunVodService {

    @Autowired
    private AliyunVodConfiguration aliyunVodConfiguration;

    @Override
    public String uploadVideo(MultipartFile file) {
        DefaultAcsClient client = aliyunVodConfiguration.acsClient();
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(file.getOriginalFilename());
        request.setFileName(file.getOriginalFilename());
        CreateUploadVideoResponse response = new CreateUploadVideoResponse();
        try {
            response = client.getAcsResponse(request);
            System.out.println(response.getVideoId());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response.getVideoId();
    }

    @Override
    public void deleteVideo(String videoSourceId) {
        DefaultAcsClient client = aliyunVodConfiguration.acsClient();
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoSourceId);
        try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(StatusCode.ERROR, "删除视频失败");
        }
    }
}
