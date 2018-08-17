package cn.allcheer.springcloud.eureka_admin_server;

import cn.allcheer.springcloud.eureka_admin_server.utils.HttpsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EurekaAdminServerApplicationTests {

	@Test
	public void contextLoads() {
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("phone", "18852951351"));
		formparams.add(new BasicNameValuePair("pwd", "941218"));
		formparams.add(new BasicNameValuePair("crawl_type", "ypz"));

		formparams = HttpsUtils.sortNameValuePair(formparams);

		formparams.add(new BasicNameValuePair("callback","http://192.168.2.69:18000/ypzCallBackController"));

		StringBuilder stringBuilder=new StringBuilder();
		for (int i=0 ;i<formparams.size();i++){
			stringBuilder.append(formparams.get(i).getName()).append("=").append(formparams.get(i).getValue()).append("&");
		}
		stringBuilder.replace(stringBuilder.lastIndexOf("&"),stringBuilder.length(),"");
		log.info("stringBuilder===="+stringBuilder);
		String signString="";
		try {
			signString = DigestUtils.md5Hex(stringBuilder.toString().getBytes("UTF-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			log.error("对签名字符串转码出错，信息是:{}",e);
		}
		formparams.add(new BasicNameValuePair("number","10010"));
		formparams.add(new BasicNameValuePair("autograph",signString));
		HttpsUtils.sendByHttp(formparams,"http://192.168.2.70:5003/api/receive_ypz");
	}

}
