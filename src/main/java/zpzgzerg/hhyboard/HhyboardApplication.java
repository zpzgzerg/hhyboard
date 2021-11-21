package zpzgzerg.hhyboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import zpzgzerg.hhyboard.dto.PagingDto;

@EnableJpaAuditing
@SpringBootApplication
public class HhyboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhyboardApplication.class, args);
    }

}
