package be.kdg.prog3.upvote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("be.kdg.prog3.upvote")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
