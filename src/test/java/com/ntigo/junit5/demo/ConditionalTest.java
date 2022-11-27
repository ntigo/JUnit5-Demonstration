package com.ntigo.junit5.demo;

import static org.junit.jupiter.api.Assumptions.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @DisplayName("조건에 따른 테스트 실행 - AssumeTrue 테스트")
    void assumeTest() {
        int a = 10;
        String runningEnv = System.getProperty( "running.env" );
        assumeTrue( "DEV".equalsIgnoreCase( runningEnv ) );
        assertTrue( a <= 10, () -> "is not less than 10" );
    }

    @Test
    @DisplayName("조건에 따른 테스트 실행 - assumingThat 테스트")
    void assumingThatTest() {
        int a = 10;
        String runningEnv = System.getProperty( "running.env" );

        assumingThat( "DEV".equalsIgnoreCase( runningEnv ), () -> {
            System.out.println( "executed lambda area." );
            assertTrue( a <= 10, () -> "is not less than 10" );
        } );
    }

    @Test
    @EnabledIfSystemProperty(named = "running.env", matches = "DEV")
    void systemPropertyTest() {
        System.out.println( "systemPropertyTest method executed." );
    }

    @Test
    @EnabledOnOs({ OS.MAC, OS.WINDOWS })
    void doTestForOS() {
        System.out.println( "doTestForOS method executed." );
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_11)
    void doTestForJRE() {
        System.out.println( "doTestForJRE method executed." );
    }

    @Test
    @EnabledIf("com.ntigo.junit5.demo.ExternalCondition#customCondition")
    void doTestForCustomCondition() {
        System.out.println( "doTestForCustomCondition method executed." );
    }

    @Test
    @Disabled("#123 이슈가 종료되기 전까지 테스트 중지")
    void doDisabledTest() {
        System.out.println( "doDisabledTest method executed." );
    }
}

class ExternalCondition {
    static boolean customCondition() {
        return true;
    }
}
