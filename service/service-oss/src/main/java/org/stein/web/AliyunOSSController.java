package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.stein.result.Result;
import org.stein.service.AliyunOSSService;

/**
 * @author stein
 * @date 2024/3/8
 */
@CrossOrigin
@RequestMapping("/edu/oss/file")
@RestController
public class AliyunOSSController {
    @Autowired
    private AliyunOSSService aliyunOssService;

    @PostMapping
    public Result uploadFile(@RequestBody MultipartFile file) {
        String url = aliyunOssService.uploadFile(file);
        return Result.ok().data("url", url);
    }
}
