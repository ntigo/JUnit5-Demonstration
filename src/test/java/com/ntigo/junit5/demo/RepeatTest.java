package com.ntigo.junit5.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.util.Random;

public class RepeatTest {

    @RepeatedTest(10)
    @DisplayName("단순 반복 테스트")
    void repeatedTest() {
        Random random = new Random();
        int rand = random.nextInt( 10 ) + 1;
        System.out.println( rand );
        assertTrue( rand >= 1 && rand <= 10, () -> "is not valid number." );
    }

    @RepeatedTest( value = 10, name = "{displayName}: {currentRepetition}/{totalRepetitions}")
    @DisplayName("단순 반복 테스트")
    void repeatedTest( RepetitionInfo repetitionInfo ) {
        Random random = new Random();
        int rand = random.nextInt( 10 ) + 1;
        System.out.println( rand + " " + repetitionInfo.getCurrentRepetition() + "/"
                + repetitionInfo.getTotalRepetitions() );
        assertTrue( rand >= 1 && rand <= 10, () -> "is not valid number." );
    }
}
