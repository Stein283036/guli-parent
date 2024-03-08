package org.stein.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.stein.config.AliyunOssConfiguration;
import org.stein.service.AliyunOSSService;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author stein
 * @date 2024/3/8
 */
@Service
public class AliyunOSSServiceImpl implements AliyunOSSService {
    @Autowired
    private AliyunOssConfiguration aliyunOssConfiguration;

    @Override
    public String uploadFile(MultipartFile file) {
        String endpoint = aliyunOssConfiguration.getEndpoint();
        String keyId = aliyunOssConfiguration.getKeyId();
        String keySecret = aliyunOssConfiguration.getKeySecret();
        String bucketName = aliyunOssConfiguration.getBucketName();

        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);
        String filename = UUID.randomUUID().toString().replaceAll("-", "")
                + file.getOriginalFilename();
        String dateDir = new DateTime().toString("yyyy/MM/dd");
        filename = dateDir + "/" + filename;

        try (InputStream is = file.getInputStream()) {
            ossClient.putObject(bucketName, filename, is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return "https://" + bucketName + "." + endpoint + "/" + filename;
    }
}
