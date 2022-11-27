package com.ntigo.junit5.demo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssertjTest {

    @Test
    @DisplayName("AssertJ 표현 방식")
    void multiplyTest() {
        assertThat( 4 * 5 ).isEqualTo( 20 );
    }

    @Test
    @DisplayName("AssertJ Exception 처리")
    void exceptionTest() {
        assertThatThrownBy( () -> new Person( "Kim", "1970.1.1" ) )
                .isInstanceOf( IllegalArgumentException.class );
    }
}
