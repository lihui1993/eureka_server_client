package cn.allcheer.springcloud.eureka_admin_server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lihui
 */
@Slf4j
@RestController
public class YpzCallBackController {
    @RequestMapping("ypzCallBackController")
    public String callBack(HttpServletRequest request){
        Map<String,String[]> map = request.getParameterMap();
        for(String key:map.keySet()){
            log.info("key="+key+"values="+map.get(key)[0]);
        }
        return "{\"code\":\"00\",\"msg\":\"success\"}";
    }
}
