package jiwon.learn_spring_validation;

import jiwon.learn_spring_validation.properties.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties({
        DatabaseProperties.class
})
@SpringBootApplication
public class LearnSpringValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringValidationApplication.class, args);
    }

}
