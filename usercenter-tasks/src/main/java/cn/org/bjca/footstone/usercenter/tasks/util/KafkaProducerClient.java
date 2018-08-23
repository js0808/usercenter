package cn.org.bjca.footstone.usercenter.tasks.util;

import java.util.Properties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:KAFKA CLIENT
 * @author: ZHAOZHIWEI
 * @create: 2018/8/23
 **/
@Component
@Slf4j
@ConfigurationProperties(prefix = "kafka")
@Data
public class KafkaProducerClient implements InitializingBean {

  private String bootstrapServers = null;

  private Producer<String, String> producer;

  @Override
  public void afterPropertiesSet() throws Exception {
    init();
  }

  public void init() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.ACKS_CONFIG, "1");
//      props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//      props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//      props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
    props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    producer = new KafkaProducer(props);
  }


  public boolean sendMessage(String topicName, String jsonMsg) {
    return sendMessage(topicName, null, jsonMsg);
  }

  public boolean sendMessage(String topicName, String key, String jsonMsg) {
    try {
      producer.send(new ProducerRecord<>(topicName, key, jsonMsg));
      log.info("send kafka msg success, topic={}, key={}, msg={}", topicName, key, jsonMsg);
      return true;
    } catch (Exception e) {
      log.error("send kafka msg error, topic={}, key={}, msg={}", topicName, key, jsonMsg, e);
    }
    return false;
  }
}
