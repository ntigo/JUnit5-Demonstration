package com.ntigo.junit5.demo;

import org.junit.jupiter.api.*;

// 테스트 메서드의 이름 표기중 _ 언더 스코어를 공백으로 대체하는 전략 사용
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
public class NamingTest {

    @Test
    void firstTest() {
        System.out.println( "first method executed." );
    }

    @Test
    void secondTest() {
        System.out.println( "second method executed." );
    }

    @Test
    void thirdTest() {
        System.out.println( "third method executed." );
    }

    // for replace under scores
    @Test
    void fourth_test() {
        System.out.println( "fourth method executed." );
    }

    @Test
    // 이름을 지정하여 사용할 수 있고, 이름 표기 전략보다 높은 우선 순위를 갖는다.
    @DisplayName( "다섯 번째 테스트" )
    void fifthTest() {
        System.out.println( "fifth method executed." );
    }
}
