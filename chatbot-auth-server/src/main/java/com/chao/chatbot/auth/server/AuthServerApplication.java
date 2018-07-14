package com.chao.chatbot.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author:WangYichao
 * @Description:
 * @Date:Created in 2018/7/12 21:57
 */
@SpringBootApplication
@EnableAuthorizationServer
@EnableDiscoveryClient
public class AuthServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthServerApplication.class,args);
    }
}
