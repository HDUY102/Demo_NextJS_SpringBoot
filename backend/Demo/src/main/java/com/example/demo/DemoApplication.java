package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import java.util.TimeZone;
@SpringBootApplication
@ComponentScan(basePackages = "com.example") 	
public class DemoApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh")); // Set time zone at HoChiMinh (UTC +7)
		SpringApplication.run(DemoApplication.class, args);
	}
}
