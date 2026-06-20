package com.learn.spring.config.properties.profileproperties;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfilePropertiesTest.TestApplication.class)
@ActiveProfiles({"production", "test"})
public class ProfilePropertiesTest {

    @Autowired
    private TestApplication.ProfileProperties profileProperties;

    @Test
    void nameProfileProperties() {
        Assertions.assertEquals("Default", profileProperties.defaultFile);
        Assertions.assertEquals("Production", profileProperties.productionFile);
        Assertions.assertEquals("Test", profileProperties.testFile);
    }

    @SpringBootApplication
    public static class TestApplication {

        @Getter
        @Component
        public static class ProfileProperties {

            @Value("${profile.default}")
            private String defaultFile;

            @Value("${profile.production}")
            private String productionFile;

            @Value("${profile.test}")
            private String testFile;

        }

    }


}
