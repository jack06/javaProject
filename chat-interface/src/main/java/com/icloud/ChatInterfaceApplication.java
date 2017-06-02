package com.icloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@EnableDiscoveryClient /**注册eureka服务**/
@MapperScan("com.icloud.dao")/** 扫描mybatis mapper接口 */
@PropertySource("classpath:jdbc.properties")
@PropertySource("classpath:config.properties")
@EnableTransactionManagement/**启用注解事务管理**/



public class ChatInterfaceApplication {	

	public static void main(String[] args) {
		SpringApplication.run(ChatInterfaceApplication.class, args);
	}
	
}
