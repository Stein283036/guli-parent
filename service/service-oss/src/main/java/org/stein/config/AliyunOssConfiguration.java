package org.stein.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author stein
 * @date 2024/3/8
 */
@Data
@ConfigurationProperties(prefix = "aliyun.oss.file")
@Configuration
public class AliyunOssConfiguration {
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
}
