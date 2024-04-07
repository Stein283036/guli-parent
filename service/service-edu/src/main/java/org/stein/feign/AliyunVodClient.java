package org.stein.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.stein.feign.impl.AliyunVodClientImpl;

/**
 * @author stein
 * @date 2024/3/26
 */
@FeignClient(value = "service-vod", fallback = AliyunVodClientImpl.class)
public interface AliyunVodClient {
    @DeleteMapping("/edu/vod/file/{videoSourceId}")
    void deleteVideo(@PathVariable("videoSourceId") String videoSourceId);
}
