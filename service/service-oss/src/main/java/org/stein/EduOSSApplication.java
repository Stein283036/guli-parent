package org.stein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author stein
 * @date 2024/3/8
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EduOSSApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduOSSApplication.class, args);
    }
}
