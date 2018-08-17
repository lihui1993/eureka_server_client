package cn.allcheer.springcloud.eureka_feign_consumer.hystrixfallback;

import cn.allcheer.springcloud.eureka_feign_consumer.service.HomeClientService;
import org.springframework.stereotype.Component;

/**
 * @author lihui
 */
@Component
public class HomeClientServiceFallBack implements HomeClientService {
    @Override
    public String consumer() {
        return "feign + hystrix faild";
    }
}
