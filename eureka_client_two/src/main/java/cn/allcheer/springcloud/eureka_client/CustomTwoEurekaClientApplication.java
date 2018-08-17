package cn.allcheer.springcloud.eureka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lihui
 */
@SpringBootApplication
@EnableEurekaClient
public class CustomTwoEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomTwoEurekaClientApplication.class, args);
	}
}
