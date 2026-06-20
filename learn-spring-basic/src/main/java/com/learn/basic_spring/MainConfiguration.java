package com.learn.basic_spring;

import com.learn.basic_spring.configuration.BarConfiguration;
import com.learn.basic_spring.configuration.FooConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        FooConfiguration.class,
        BarConfiguration.class
})

public class MainConfiguration {
}
