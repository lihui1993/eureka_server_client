package cn.allcheer.springcloud.eureka_admin_server;

import cn.allcheer.springcloud.eureka_admin_server.utils.HttpsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EurekaAdminServerApplicationTests {

	@Autowired
	private RestTemplate restTemplate;
	@Test
	public void contextLoads() {
	}

}
