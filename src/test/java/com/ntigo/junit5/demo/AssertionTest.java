package com.ntigo.junit5.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionTest {

    String getHighCostString() {
        try {
            TimeUnit.SECONDS.sleep( 3 );
        } catch ( InterruptedException e ) {
            throw new RuntimeException( e );
        }
        return "must be not null.";
    }

    @Test
    void normalNotNullTest() {
        Person person = new Person( "Kim", "1984.11.05" );
        assertNotNull( person, getHighCostString() );
    }

    @Test
    @DisplayName("lazy evaluation을 위한 Supplier 활용")
    void notNullTest() throws InterruptedException {
        Person person = new Person( "Kim", "1984.11.05" );
        assertNotNull( person, () -> getHighCostString() );
    }

    int multiply( int x, int y ) {
        return x * y;
    }

    @Test
    void equalsTest() {
        assertEquals( 16, multiply( 3, 5 ), () -> "is not correct result." );
    }

    @Test
    @DisplayName("여러 개의 테스트가 중첩될 때 중간에 실패하는 경우")
    void failWhileTest() throws InterruptedException {
        Person person = new Person( "Kim", "1984.11.05" );
        assertNotNull( person, () -> getHighCostString() );
        System.out.println( "first step" );
        assertEquals( "Lee", person.getName() ); // expected fail case.
        System.out.println( "second step" );
        assertEquals( 55, person.getWeight() );
        System.out.println( "end" );
    }

    // 연관된 테스트를 한 번에 실행하는 방법
    @Test
    @DisplayName("assertAll을 이용한 모든 테스트 실행하기")
    void executionAllTest() {
        Person person = new Person( "Kim", "1984.11.05" );
        assertAll(
                () -> assertNotNull( person, () -> getHighCostString() ),
                () -> assertEquals( "Lee", person.getName() ),
                () -> assertEquals( 55, person.getWeight() ) );
    }

    private void delayedSupplier() throws InterruptedException {
        TimeUnit.SECONDS.sleep( 3 );
    }

    @Test
    @DisplayName( "Timeout 테스트" )
    void timeoutTest() {
        // 제한 시간 이후에도 모든 루틴의 수행을 보장
        assertTimeout( Duration.ofSeconds( 1 ), () -> delayedSupplier() );
    }

    @Test
    @DisplayName( "TimeoutPreemptively 테스트" )
    void timeoutPreemptivelyTest() {
        // 제한 시간 이후에는 루틴을 수행하지 않음.
        assertTimeoutPreemptively( Duration.ofSeconds( 1 ), () -> delayedSupplier() );
    }
}
