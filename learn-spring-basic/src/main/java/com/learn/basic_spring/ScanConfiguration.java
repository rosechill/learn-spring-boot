package com.learn.basic_spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.learn.basic_spring.configuration"
})
public class ScanConfiguration {
}
