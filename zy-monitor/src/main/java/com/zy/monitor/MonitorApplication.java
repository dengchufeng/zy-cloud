package com.zy.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动类
 *
 * @Author: xc
 * @Date: 2023/8/17 11:12
 */
@Slf4j
@EnableAdminServer
@SpringBootApplication
public class MonitorApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(MonitorApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("Application init is running! Access URLs:\n\t" +
                "External: \thttp://" + ip + ":" + port + "/hello" + "\n\t" +
                "----------------------------------------------------------");
    }

}
