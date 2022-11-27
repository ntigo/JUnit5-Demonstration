package com.ntigo.junit5.demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderingTest {

    // first > third > second 순으로 실행됨.
    @Order(1)
    @Test
    void firstDeclaredTest() {
        System.out.println( "firstDeclaredTest" );
    }

    @Order(3)
    @Test
    void secondDeclaredTest() {
        System.out.println( "secondDeclaredTest" );
    }

    @Order(2)
    @Test
    void thirdDeclaredTest() {
        System.out.println( "thirdDeclaredTest" );
    }
}
