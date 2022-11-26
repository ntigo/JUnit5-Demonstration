package com.ntigo.junit5.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BasicTest {

    @Test
    void createPersonTest() {
        Person person = new Person( "Kim", "1984.11.05" );
        assertNotNull( person );
    }

    @Test
    void secondTest() {
        System.out.println( "secondTest executed." );
    }

    // 테스트 실행 이전 1회 실행
    @BeforeAll
    static void beforeAll() {
        System.out.println( "before all executed.");
    }

    // 테스트 실행 이후 1회 실행
    @AfterAll
    static void afterAll() {
        System.out.println( "after all executed.");
    }

    // 지정된 각 테스트 이전 매번 1회 실행
    @BeforeEach
    void beforeEach() {
        System.out.println( "before each executed.");
    }

    // 지정된 각 테스트 이후 매번 1회 실행
    @AfterEach
    void afterEach() {
        System.out.println( "after each executed.");
    }
}