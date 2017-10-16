package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


/**
 * The main class of the project.
 * By running the main class of {@link Application} then you start the Spring Boot system
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
