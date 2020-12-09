package se.joeldegerman.javaeewebshop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SpringFoxSwaggerConfig {
    @Bean
    public Docket config() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("se.joeldegerman.javaeewebshop.controllers.rest"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("E-Commerce API",
                "API endpoints for this E-Commerce API",
                "1.0", "Free to use",
                new Contact("Joel Degerman",
                        "http://joeldegerman.se",
                        "joel@joeldegerman.se"),
                "API License", "http://joeldegerman.se",
                Collections.emptyList());
    }
}
