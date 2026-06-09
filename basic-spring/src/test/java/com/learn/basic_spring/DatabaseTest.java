package com.learn.basic_spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseTest {
    // konsep singleton ya, jadi walaupun variabel nya beda itu tetap sama
    @Test
    void testSingleton() {
        var database1 = Database.getInstance();
        var database2 = Database.getInstance();

        Assertions.assertSame(database1, database2);

    }

}
