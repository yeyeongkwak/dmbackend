package com.spring.dm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.service","com.spring.api"})
@MapperScan(basePackages = {"com.spring.mapper"})
public class DmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmApplication.class, args);
	}

}
