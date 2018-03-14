import com.sample.spring.itegration.kafka.configuration.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ProducingChannelConfig.class, ConsumingChannelConfig.class, KafkaConfiguration.class})
public class Application implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... strings) throws Exception {

  }
}
