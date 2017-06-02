package com.icloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableDiscoveryClient /**注册eureka服务**/
@MapperScan("com.icloud.dao")/** 扫描mybatis mapper接口 */
@PropertySource({"classpath:jdbc.properties","classpath:config.properties"})
@EnableTransactionManagement/**启用注解事务管理**/

public class PekingterfaceApplication {	

	public static void main(String[] args) {
		SpringApplication.run(PekingterfaceApplication.class, args);
	}
}	
