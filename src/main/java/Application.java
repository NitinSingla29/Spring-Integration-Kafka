import com.sample.spring.itegration.kafka.configuration.consumer.ConsumingChannelConfig;
import com.sample.spring.itegration.kafka.configuration.producer.ProducingChannelConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Import({ProducingChannelConfig.class, ConsumingChannelConfig.class})
@ComponentScan(basePackages = {"com.sample.spring.itegration.kafka.configuration"})
public class Application implements CommandLineRunner {
  public static Logger logger = LoggerFactory.getLogger(Application.class);
  private final CountDownLatch latch = new CountDownLatch(3);
  @Autowired
  private KafkaTemplate<String, String> template;
  @Value("${kafka.topic.test1}")
  private String testTopic;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... strings) throws Exception {
    this.template.send(testTopic, "foo1");
    this.template.send(testTopic, "foo2");
    this.template.send(testTopic, "foo3");
    latch.await(60, TimeUnit.SECONDS);
    logger.info("All received");
  }
}
