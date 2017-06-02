package com.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServerRegisterApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ServerRegisterApplication.class).web(true).run(args);
	}
   
}
