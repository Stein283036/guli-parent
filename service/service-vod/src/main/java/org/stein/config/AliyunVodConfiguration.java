package org.stein.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author stein
 * @date 2024/3/21
 */
@Data
@ConfigurationProperties(prefix = "aliyun.cloud")
@Configuration
public class AliyunVodConfiguration {
    private String accessKeyId;
    private String accessKeySecret;

    @Bean
    public DefaultAcsClient acsClient() {
        // 点播服务接入地域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }
}
