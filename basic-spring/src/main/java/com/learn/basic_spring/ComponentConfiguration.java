package com.learn.basic_spring;

import com.learn.basic_spring.data.MultiFoo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
        "com.learn.basic_spring.service",
        "com.learn.basic_spring.repository",
        "com.learn.basic_spring.configuration",
})

@Import(MultiFoo.class)
public class ComponentConfiguration {
}
