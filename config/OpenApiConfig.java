package mr.supnum.library_app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryAppOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library Management System API")
                        .description("API backend professionnelle pour la gestion complète d'une bibliothèque.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("SUPNUM Tech Team")
                                .url("https://www.supnum.mr")
                                .email("contact@supnum.mr"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
