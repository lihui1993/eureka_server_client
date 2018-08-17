package cn.allcheer.springcloud.eureka_client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihui
 */
@Slf4j
@RestController
public class SimpleTestController {
    @Value("${server.port}")
    String port;
    @GetMapping("/")
    public String home(){
        return "Hello world ,port:" + port;
    }
}
