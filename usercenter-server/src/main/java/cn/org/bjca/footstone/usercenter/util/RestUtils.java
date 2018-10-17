package cn.org.bjca.footstone.usercenter.util;

import lombok.val;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/4/12
 * @since 1.0
 */
public class RestUtils {

  private static final RestTemplate REST = ApplicationContextHolder.get()
      .getBean(RestTemplate.class);

  public static <T> ResponseEntity<T> get(String url, Class<T> type) {
    return get(url, true, type);
  }

  public static <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> type) {
    return get(url, true, type);
  }

  public static <T> ResponseEntity<T> get(String url, boolean useCookie,
      ParameterizedTypeReference<T> type) {
    HttpEntity entity = createEntity(useCookie, null);
    return REST.exchange(url, HttpMethod.GET, entity, type);
  }

  public static <T> ResponseEntity<T> get(String url, boolean useCookie, Class<T> type) {
    HttpEntity entity = createEntity(useCookie, null);
    return REST.exchange(url, HttpMethod.GET, entity, type);
  }

  public static <T> ResponseEntity<T> post(String url, Class<T> type, Object body) {
    return post(url, true, type, body);
  }

  public static <T> ResponseEntity<T> post(String url, ParameterizedTypeReference<T> type,
      Object body) {
    return post(url, true, type, body);
  }

  public static <T> ResponseEntity<T> post(String url, boolean useCookie,
      ParameterizedTypeReference<T> type,
      Object body) {
    HttpEntity entity = createEntity(useCookie, body);
    return REST.exchange(url, HttpMethod.POST, entity, type);
  }

  public static <T> ResponseEntity<T> post(String url, boolean useCookie, Class<T> type,
      Object body) {
    HttpEntity entity = createEntity(useCookie, body);
    return REST.exchange(url, HttpMethod.POST, entity, type);
  }

  public static <T> ResponseEntity<T> put(String url, Class<T> type, Object body) {
    return put(url, true, type, body);
  }

  public static <T> ResponseEntity<T> put(String url, ParameterizedTypeReference<T> type,
      Object body) {
    return put(url, true, type, body);
  }

  public static <T> ResponseEntity<T> put(String url, boolean useCookie, Class<T> type,
      Object body) {
    HttpEntity entity = createEntity(useCookie, body);
    return REST.exchange(url, HttpMethod.PUT, entity, type);
  }

  public static <T> ResponseEntity<T> put(String url, boolean useCookie,
      ParameterizedTypeReference<T> type,
      Object body) {
    HttpEntity entity = createEntity(useCookie, body);
    return REST.exchange(url, HttpMethod.PUT, entity, type);
  }

  public static <T> ResponseEntity<T> delete(String url, Class<T> type, Object body) {
    return delete(url, true, type, body);
  }

  public static <T> ResponseEntity<T> delete(String url, ParameterizedTypeReference<T> type,
      Object body) {
    return delete(url, true, type, body);
  }

  public static <T> ResponseEntity<T> delete(String url, boolean useCookie, Class<T> type,
      Object body) {
    HttpEntity entity = createEntity(useCookie, body);
    return REST.exchange(url, HttpMethod.DELETE, entity, type);
  }

  public static <T> ResponseEntity<T> delete(String url, boolean useCookie,
      ParameterizedTypeReference<T> type,
      Object body) {
    HttpEntity entity = createEntity(useCookie, body);
    return REST.exchange(url, HttpMethod.DELETE, entity, type);
  }

  private static HttpEntity createEntity(boolean useCookie, Object body) {
    val headers = new HttpHeaders();
    return new HttpEntity<>(body, headers);
  }

  public static boolean isOk(ResponseEntity entity) {
    return entity.getStatusCode() == HttpStatus.OK;
  }

  public static byte[] downLoad(String url) {
    return REST.getForObject(url, byte[].class);
  }
}
