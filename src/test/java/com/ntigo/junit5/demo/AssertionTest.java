package com.ntigo.junit5.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssertionTest {

    @Test
    void notNullTest() {
        Person person = new Person( "Kim", "1984.11.05" );
        assertNotNull( person, () -> "must be not null." );
    }

    int multiply( int x, int y ) {
        return x * y;
    }

    @Test
    void equalsTest() {
        assertEquals( 16, multiply( 3, 5 ), () -> "is not correct result." );
    }
}
