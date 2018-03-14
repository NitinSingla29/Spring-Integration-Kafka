import com.sample.spring.itegration.kafka.configuration.ConsumingChannelConfig;
import com.sample.spring.itegration.kafka.configuration.ProducingChannelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ProducingChannelConfig.class, ConsumingChannelConfig.class})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
