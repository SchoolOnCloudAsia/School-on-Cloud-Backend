package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void testAddition() {
        int a = 7;
        int b = 10;
        int expected = 17;
        int actual = a + b;
        assertEquals(expected, actual, "5 + 10 should equal 15");
    }
}