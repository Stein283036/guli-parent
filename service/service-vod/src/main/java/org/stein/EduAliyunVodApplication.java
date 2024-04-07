package org.stein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author stein
 * @date 2024/3/21
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EduAliyunVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduAliyunVodApplication.class);
    }
}
