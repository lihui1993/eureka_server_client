package cn.allcheer.springcloud.eureka_admin_server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lihui
 */
@SpringBootApplication
@EnableAdminServer
public class EurekaAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaAdminServerApplication.class, args);
	}
}
