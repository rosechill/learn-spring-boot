package com.learn.basic_spring;

import com.learn.basic_spring.listener.LoginAgainSuccessListener;
import com.learn.basic_spring.listener.LoginSuccessListener;
import com.learn.basic_spring.listener.UserListener;
import com.learn.basic_spring.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class EventListenerTest {

    @Configuration
    @Import({
            UserService.class,
            LoginSuccessListener.class,
            LoginAgainSuccessListener.class,
            UserListener.class,
    })
    public static class TestConfiguration {
    }

    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
        applicationContext.registerShutdownHook();
    }

    @Test
    void testEvent() {
        UserService userService = applicationContext.getBean(UserService.class);
        userService.login("eko", "eko");
        userService.login("eko", "salah");
        userService.login("eko", "salah");
    }

}
