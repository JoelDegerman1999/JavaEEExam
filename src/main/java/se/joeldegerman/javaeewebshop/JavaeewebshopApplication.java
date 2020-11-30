package se.joeldegerman.javaeewebshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import se.joeldegerman.javaeewebshop.models.Category;
import se.joeldegerman.javaeewebshop.repositories.CategoryRepository;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;

@SpringBootApplication
@ConfigurationPropertiesScan("se.joeldegerman.javaeewebshop.security.jwt")
public class JavaeewebshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeewebshopApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(CategoryRepository c, ProductRepository p) {
        return args -> {
        };
    }
}
