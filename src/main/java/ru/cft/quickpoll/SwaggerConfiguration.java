package ru.cft.quickpoll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    // http://localhost:8080/swagger-ui/

    @Bean
    public Docket apiV1() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/v1/*.*"))
                .build()
                .apiInfo(apiInfo("v1"))
                .groupName("v1");
        docket.useDefaultResponseMessages(false);

        return docket;
    }

    @Bean
    public Docket apiV2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/v2/*.*"))
                .build()
                .apiInfo(apiInfo("v2"))
                .groupName("v2");
        docket.useDefaultResponseMessages(false);

        return docket;
    }

    private ApiInfo apiInfo(String version) {
        return new ApiInfo(
                "QuickPoll REST API",
                "QuickPoll Api for creating and managing polls",
                version,
                "Terms of service",
                new Contact("Alifirov", "www.example.com", "info@example.com"),
                "MIT License", "http://opensource.org/licenses/MIT", Collections.emptyList());
    }
}
