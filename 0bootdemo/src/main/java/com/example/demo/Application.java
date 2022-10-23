package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication		// 스프링부트 애플리케이션의 실행 프로그램
public class Application {

	public static void main(String[] args) {	
		// 메인 메소드를 실행 -> 브라우저에서 localhost:8081(url) 엔터
		SpringApplication.run(Application.class, args);
	}

}
