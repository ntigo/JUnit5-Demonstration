package com.ntigo.junit5.demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration( DisplayNameGenerator.ReplaceUnderscores.class )
class MemberTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

    @Test
    @DisplayName( "메소드 생성 테스트" )
    void create_test() {
        Member member = new Member( "Kim", "1994.11.05" );
        System.out.println("createTest");
        assertNotNull( member, "object is null." );
    }

    @Test
    void second_test_test() {
        Member member = new Member( "Kim", "1994.11.05" );
        System.out.println("second test");
        assertNotNull( member, "object is null." );
    }

    int multiply(int x, int y) {
        return x * y;
    }

    String getHighCost() {
//        try {
//            TimeUnit.SECONDS.sleep( 3 );
//        } catch ( InterruptedException e ) {
//            throw new RuntimeException( e );
//        }

        return "is not valid result.";
    }

    @Test
    void multiplyTest() {
        System.out.println( this );
        assertEquals( 15, multiply( 3, 5 ), () -> getHighCost() );
        assertEquals( 17, multiply( 3, 5 ), () -> getHighCost() );
        assertEquals( 18, multiply( 3, 5 ), () -> getHighCost() );
    }

    @Test
    void multiplyAssertAll() {
        System.out.println( this );
        assertAll( () -> assertEquals( 15, multiply( 3, 5 ), () -> getHighCost() ),
                () -> assertEquals( 16, multiply( 3, 5 ), () -> getHighCost() ),
                () -> assertEquals( 17, multiply( 3, 5 ), () -> getHighCost() ));
    }

    @Test
//    @EnabledIf( "com.ntigo.junit5.demo.ExternalConditions#isContinue" )
    void throwsTest() {
        System.out.println( this );
        Throwable t = assertThrows( IllegalArgumentException.class, () -> new Member( "Kim", "1984.11.05" ) );
        System.out.println(t.getMessage());
    }
}
