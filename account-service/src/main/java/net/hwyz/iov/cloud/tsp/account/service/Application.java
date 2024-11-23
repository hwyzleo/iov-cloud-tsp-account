package net.hwyz.iov.cloud.tsp.account.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author hwyz_leo
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // nacos issue，增加下面避免nacos日志警告
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(Application.class, args);
        logger.info("应用启动完成");
    }

}
