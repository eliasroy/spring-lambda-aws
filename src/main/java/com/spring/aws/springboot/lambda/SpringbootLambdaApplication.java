package com.spring.aws.springboot.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "com.spring.aws.springboot.lambda")

public class SpringbootLambdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLambdaApplication.class, args);
	}

}
