package suheee.baguniguba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BagunigubaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BagunigubaApplication.class, args);
    }

}
