package cn.allcheer.springcloud.eureka_zuul;

import cn.allcheer.springcloud.eureka_zuul.config.CustomTokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author lihui
 */
@EnableZuulProxy
@SpringBootApplication
public class EurekaZuulApplication {

	@Bean
	public CustomTokenFilter customTokenFilter(){
		return new CustomTokenFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaZuulApplication.class, args);
	}
}
