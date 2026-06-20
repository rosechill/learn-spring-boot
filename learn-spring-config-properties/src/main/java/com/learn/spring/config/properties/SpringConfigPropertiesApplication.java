package com.learn.spring.config.properties;

import com.learn.spring.config.properties.converter.StringToDateConverter;
import com.learn.spring.config.properties.properties.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;

@SpringBootApplication
@EnableConfigurationProperties({
        ApplicationProperties.class,
})
public class SpringConfigPropertiesApplication {

    @Bean
    public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
        ApplicationConversionService applicationConversionService = new ApplicationConversionService();
        applicationConversionService.addConverter(stringToDateConverter);
        return applicationConversionService;

    }

    public static void main(String[] args) {
        SpringApplication.run(SpringConfigPropertiesApplication.class, args);
    }

}
