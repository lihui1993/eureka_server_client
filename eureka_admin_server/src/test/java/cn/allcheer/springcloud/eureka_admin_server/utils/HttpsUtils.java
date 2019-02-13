package cn.allcheer.springcloud.eureka_admin_server.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
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
        } catch (NoSuchAlgorithmException e) {
        } catch (KeyStoreException e) {
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
            RequestConfig requestConfig=RequestConfig.custom().setConnectTimeout(1000*60*3).setConnectionRequestTimeout(1000*60*5).setSocketTimeout(1000*60*10).build();
            httpPost.setConfig(requestConfig);
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String respString = EntityUtils.toString(httpEntity, "UTF-8");
                log.info("jsObject\n{}",respString);
                return respString;
            } else {
                return null;
            }
        } catch (Exception e) {
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

}
