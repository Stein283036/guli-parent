package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.stein.result.Result;
import org.stein.service.AliyunVodService;

/**
 * @author stein
 * @date 2024/3/21
 */
@CrossOrigin
@RequestMapping("/edu/vod/file")
@RestController
public class AliyunVodController {
    @Autowired
    private AliyunVodService aliyunVodService;

    @DeleteMapping("/{videoSourceId}")
    public Result deleteVideo(@PathVariable("videoSourceId") String videoSourceId) {
        aliyunVodService.deleteVideo(videoSourceId);
        return Result.ok();
    }

    @PostMapping
    public Result uploadVideo(@RequestBody MultipartFile file) {
        String videoSourceId = aliyunVodService.uploadVideo(file);
        return Result.ok().data("videoSourceId", videoSourceId);
    }
}
