package cn.org.bjca.footstone.usercenter.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 连接池配置
 * @Author:baoqb
 * @Date:18/6/28
 */
@Configuration
@ConfigurationProperties(prefix = "spring.restpool")
@Data
@Slf4j
public class RestTemplateConfig {

  private int maxTotal;
  private int maxPerRouter;
  private int socketTimeOut;
  private int connectionTimeOut;
  private int connectionRequestTimeOut;
  private boolean ignorssl;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate(httpRequestFactory());
  }

  @Bean
  public ClientHttpRequestFactory httpRequestFactory() {
    return new HttpComponentsClientHttpRequestFactory(httpClient());
  }

  @Bean
  public HttpClient httpClient() {

    RegistryBuilder<ConnectionSocketFactory> register = RegistryBuilder.<ConnectionSocketFactory>create()
        .register("http", PlainConnectionSocketFactory.getSocketFactory());
    if (ignorssl) {
      try {
        // 信任所有
        log.info("忽略SSL认证");
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
            (chain, authType) -> true).build();
        register.register("https", new SSLConnectionSocketFactory(sslContext));
      } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
        log.error("初始化httpclient异常", e);
        register.register("https", SSLConnectionSocketFactory.getSocketFactory());
      }
    }
    Registry<ConnectionSocketFactory> registry = register.build();
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
        registry);
    //设置整个连接池最大连接数 根据自己的场景决定
    connectionManager.setMaxTotal(maxTotal);
    //路由是对maxTotal的细分
    connectionManager.setDefaultMaxPerRoute(maxPerRouter);
    RequestConfig requestConfig = RequestConfig.custom()
        .setSocketTimeout(socketTimeOut) //服务器返回数据(response)的时间，超过该时间抛出read timeout
        .setConnectTimeout(connectionTimeOut)//连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
        .setConnectionRequestTimeout(
            connectionRequestTimeOut)//从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
        .build();

    return HttpClientBuilder.create()
        .setDefaultRequestConfig(requestConfig)
        .setConnectionManager(connectionManager).build();
  }
}
