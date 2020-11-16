package se.joeldegerman.javaeewebshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;

@SpringBootApplication
public class JavaeewebshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeewebshopApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(UserRepository repository) {
        return args -> {
        };
    }
}
