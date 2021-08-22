package com.springboot.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

   /* @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rabbitmq.springbootrabbitmq"))
                .build();
    }*/
}
