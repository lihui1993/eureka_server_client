package cn.allcheer.springcloud.eureka_ribbon_consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author lihui
 */
@RestController
public class HomeClientController {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultFallBack")
    @GetMapping("/home")
    public String hello(){
        return restTemplate.getForObject("http://eureka-client-provider/",String.class);
    }
    public String defaultFallBack(){
        return "ribbon + hystrix faild";
    }
}
