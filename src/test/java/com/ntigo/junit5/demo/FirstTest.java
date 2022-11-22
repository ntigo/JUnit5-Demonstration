package com.ntigo.junit5.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstTest {

    @Test
    void createPersonTest()
    {
        Person person = new Person( "Kim", "1984.11.05" );
        assertTrue( person != null, "object is not valid." );
    }
}