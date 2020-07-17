package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.io.Reader;

@SpringBootApplication
@MapperScan(value = {"org.example.dao"})
public class CarsysApplication {

	public static void main(String[] args) {

		SpringApplication.run(CarsysApplication.class, args);
	}

}
