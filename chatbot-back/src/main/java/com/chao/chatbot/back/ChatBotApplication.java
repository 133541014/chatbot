
package com.chao.chatbot.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * spring boot项目启动器
 * 
 * @author: origen.wang
 * @date: 2017年7月26日
 */

@SpringBootApplication
@MapperScan("com.chao.chatbot.back.mapper")
@EnableAsync
@EnableEurekaClient
public class ChatBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBotApplication.class, args);
	}

}
