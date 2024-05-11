package com.jvm.lecti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = "com.jvm.lecti.entity")
public class LectiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LectiApplication.class, args);
	}

}
