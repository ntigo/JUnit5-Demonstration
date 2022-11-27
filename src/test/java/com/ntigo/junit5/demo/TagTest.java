package com.ntigo.junit5.demo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class TagTest {

    @Test
    @Tag("Fast")
    void first() {
        System.out.println( "first" );
    }

    @Test
    @Tag("Slow")
    void second() {
        System.out.println( "second" );
    }

    @Test
    @Tag("Integration")
    void third() {
        System.out.println( "third" );
    }

    @CustomTag
    void fourth( TestInfo testInfo ) {
        System.out.println( "fourth: " + testInfo.getDisplayName() );
    }
}
