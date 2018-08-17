package cn.allcheer.springcloud.eureka_feign_consumer.service;

import cn.allcheer.springcloud.eureka_feign_consumer.hystrixfallback.HomeClientServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述: 指定这个接口所要调用的 提供者服务名称
 * @author lihui
 */
@FeignClient(value = "eureka-client-provider",fallback = HomeClientServiceFallBack.class)
public interface HomeClientService {
    @GetMapping("/")
    String consumer();
}
