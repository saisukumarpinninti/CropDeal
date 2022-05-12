package com.copdeal.Swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket productApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cropdeal.Controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "CropInfo API Documentation",
                "This is Documentation for API of Farmer  Microservice",
                "1.0",
                "https://www.edgeverve.com/componentlist/assistedge/apache-software-license/",
                new springfox.documentation.service.Contact("SaiSukumar",
                        "https://saisukumarpinninti.github.io/resume/",
                        "saisukumarpinniti+apicropdeal@gmail.com"),
                "Apache License",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }

}
