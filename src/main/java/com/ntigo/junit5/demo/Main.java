package com.ntigo.junit5.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main( String[] args ) {

        // JDK 8 ~ lambda expression examples
        // 람다는 선언할 때 동작하지 않는다.
        // 기본적으로 Supplier get(), consumer accept(), function apply(), predicate test() 호출 시 동작
        Supplier<String> supplier = () -> "String";
        Consumer<String> consumer = ( str ) -> System.out.println( str );
        Function<Integer, String> function = ( i ) -> String.valueOf( i + 2 );

        Predicate<Integer> isLessThan5 = ( i ) -> i < 5;
        Predicate<Integer> isGreaterThan1 = ( i ) -> i > 1;

        // 복합적으로 활용해서 사용할 수 있다.
        System.out.println( isGreaterThan1.and( isLessThan5 ).test( 2 ) );

        // 실제 코드의 활용 예제
        List<Member> memberList = new ArrayList<>();
        memberList.add( new Member( "Kim", "1984.11.05" ) );
        memberList.add( new Member( "Lee", "1985.11.05" ) );
        memberList.add( new Member( "Park", "1986.11.05" ) );
        memberList.add( new Member( "Choi", "1987.11.05" ) );

        List<Member> collect = memberList.stream()
                .filter( m -> getYear( m ) > 1985 )
                .collect( Collectors.toList() );

        for ( Member member : collect ) {
            System.out.println( member.getName() );
        }

        // 기본의 응용 변형 function 예제
        BiFunction<String, String, String> str = ( a, b ) -> a + b;
        BinaryOperator<String> binaryOperator = ( a, b ) -> a + b;
    }

    private static int getYear( Member m ) {
        return Integer.parseInt( new StringTokenizer( m.getBirth(), "." ).nextToken() );
    }
}