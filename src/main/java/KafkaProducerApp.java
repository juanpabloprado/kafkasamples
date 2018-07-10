import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerApp {
  private static final Logger logger = LoggerFactory.getLogger(KafkaProducerApp.class);

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092, localhost:9093");
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    try (KafkaProducer<String, String> myProducer = new KafkaProducer<>(props)) {
      for (int i = 0; i < 150; i++) {
        myProducer.send(new ProducerRecord<>("my-topic", i + "", "MyMessage: " + i));
      }
    } catch (Exception e) {
      logger.error("Failed to send kafka producer record", e);
    }
  }
}
