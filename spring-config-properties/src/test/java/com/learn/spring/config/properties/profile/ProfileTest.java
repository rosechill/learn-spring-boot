package com.learn.spring.config.properties.profile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfileTest.TestApplication.class)
@ActiveProfiles({"production"})
public class ProfileTest {

    @Autowired
    private TestApplication.sayHello sayHello;

    @Test
    void testProfile() {
        Assertions.assertEquals("Hello Eko from production", sayHello.say("Eko"));
    }

    @SpringBootApplication
    public static class TestApplication {

        public interface sayHello {
            String say(String name);
        }

        @Component
        @Profile({"local"})
        public static class SayHelloLocal implements sayHello {
            @Override
            public String say(String name) {
                return "Hello " + name + " from Local";
            }
        }

        @Component
        @Profile({"production"})
        public static class SayHelloProduction implements sayHello {
            @Override
            public String say(String name) {
                return "Hello " + name + " from production";
            }
        }

    }

}
