package jiwon.learn_spring_validation.helper;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class SayHello implements ISayHello {

    public String sayHello(@NotBlank String name) {
        return "Hello " + name;
    }

}
