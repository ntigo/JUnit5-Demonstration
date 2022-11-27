package com.ntigo.junit5.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInstanceTest {
    private int value = 1;

    // 테스트 인스턴스 지정 시 static 불필요
    @BeforeAll
    void beforeAll() {
        value++;
        System.out.println( "beforeAll - " + this + "  " + value );
    }

    // stateful 하게 사용할 수 있음.
    @Test
    void test() {
        value++;
        System.out.println( "test - " + this + "  " + value );
    }

    // 테스트 인스턴스 지정 시 static 불필요
    @AfterAll()
    void afterAll() {
        value++;
        System.out.println( "afterAll - " + this + "  " + value );
    }
}
