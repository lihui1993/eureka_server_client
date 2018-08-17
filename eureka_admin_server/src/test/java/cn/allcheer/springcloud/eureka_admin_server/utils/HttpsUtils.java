package cn.allcheer.springcloud.eureka_admin_server.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
public class HttpsUtils {

    static CloseableHttpClient httpClient;
    static CloseableHttpResponse httpResponse;

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();

    }

    /**
     * 发送https请求
     *
     */
    public static String sendByHttp(List<NameValuePair> listNVP, String url) {
        try {
            HttpPost httpPost = new HttpPost(url);

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(listNVP, "UTF-8");
            log.info("创建请求httpPost-URL="+url+"，params="+listNVP);
            httpPost.setEntity(entity);
            httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String jsObject = EntityUtils.toString(httpEntity, "UTF-8");
                log.info("jsObject\n{}",jsObject);
                return jsObject;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpResponse.close();
                httpClient.close();
                log.info("请求流关闭完成");
            } catch (IOException e) {
                log.info("请求流关闭出错"+e);
            }
        }
    }

    public static List<NameValuePair> sortNameValuePair(List<NameValuePair> list){
        list.sort((o1,o2)->{
            int ascii1 = 0;
            int ascii2 = 0;
            int result=-1;
            for(int i = 0;i<o1.getName().length() && i<o2.getName().length();i++){
                ascii1=o1.getName().charAt(i);
                ascii2=o2.getName().charAt(i);
                if(ascii1 > ascii2){
                    result = 1;
                    break;
                }else if(ascii1 == ascii2){
                    continue;
                }else if(ascii1<ascii2){
                    result=-1;
                    break;
                }
            }
            return result;
        });
        return list;
    }
}
