package cn.allcheer.springcloud.eureka_feign_consumer.controller;

import cn.allcheer.springcloud.eureka_feign_consumer.service.HomeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihui
 */
@RestController
public class HomeClientController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HomeClientService homeClientService;

    @GetMapping("/home")
    public String hello(){
        return homeClientService.consumer();
    }
}
