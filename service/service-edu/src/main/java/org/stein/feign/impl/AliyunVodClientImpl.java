package org.stein.feign.impl;

import org.springframework.stereotype.Service;
import org.stein.feign.AliyunVodClient;
import org.stein.handler.exception.GuliException;
import org.stein.result.StatusCode;

/**
 * @author stein
 * @date 2024/4/7
 */
@Service
public class AliyunVodClientImpl implements AliyunVodClient {
    @Override
    public void deleteVideo(String videoSourceId) {
        throw new GuliException(StatusCode.ERROR, "远程服务不可达");
    }
}
