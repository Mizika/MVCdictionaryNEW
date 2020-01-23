package mmtr.spring.dic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class app {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(app.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "1900"));
        app.run(args);
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("mmtr.spring.dic")).build();
    }

}