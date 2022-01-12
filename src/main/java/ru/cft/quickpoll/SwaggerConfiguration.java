package ru.cft.quickpoll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    // http://localhost:8080/swagger-ui/

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("/polls/*.*|/votes/*.*|/computeresult/*.*"))
                .build()
                .apiInfo(apiInfo());
        docket.useDefaultResponseMessages(false);

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "QuickPoll REST API",
                "QuickPoll Api for creating and managing polls",
                "http://example.com/terms-of-service",
                "Terms of service",
                new Contact("Alifirov", "www.example.com", "info@example.com"),
                "MIT License", "http://opensource.org/licenses/MIT", Collections.emptyList());
    }
}
