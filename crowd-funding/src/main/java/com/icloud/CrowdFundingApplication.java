package com.icloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.icloud.dao")/** 扫描mybatis mapper接口 */
@PropertySource({"classpath:jdbc.properties","classpath:config.properties"})
@EnableTransactionManagement/**启用注解事务管理**/
public class CrowdFundingApplication {	

	public static void main(String[] args) {
		SpringApplication.run(CrowdFundingApplication.class, args);
	}
	
}
