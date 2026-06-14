package com.learn.spring.config.properties.environment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = EnvironmentTest.TestApplication.class)
public class EnvironmentTest {

    @Autowired
    private Environment environment;

    @Test
    void testApplicationProperties() {
        String javaHome = environment.getProperty("JAVA_HOME");
        System.out.println(javaHome);
//        Assertions.assertEquals("/opt/homebrew/Cellar/openjdk@17/17.0.15/libexec/openjdk.jdk/Contents/Home", javaHome );
    }

    @SpringBootApplication
    public static class TestApplication {

    }

}
