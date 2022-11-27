package com.ntigo.junit5.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RepeatTest {

    @RepeatedTest(10)
    @DisplayName("단순 반복 테스트")
    void repeatedTest() {
        Random random = new Random();
        int rand = random.nextInt( 10 ) + 1;
        System.out.println( rand );
        assertTrue( rand >= 1 && rand <= 10, () -> "is not valid number." );
    }

    @RepeatedTest(value = 10, name = "{displayName}: {currentRepetition}/{totalRepetitions}")
    @DisplayName("단순 반복 테스트")
    void repeatedTest( RepetitionInfo repetitionInfo ) {
        Random random = new Random();
        int rand = random.nextInt( 10 ) + 1;
        System.out.println( rand + " " + repetitionInfo.getCurrentRepetition() + "/"
                + repetitionInfo.getTotalRepetitions() );
        assertTrue( rand >= 1 && rand <= 10, () -> "is not valid number." );
    }

    @ParameterizedTest(name = "{displayName}: ({index}) {0}")
    @ValueSource(strings = { "a", "b", "c" })
    void paramTest( String input ) {
        System.out.println( input );
    }

    @ParameterizedTest(name = "{displayName}: ({index})")
    @ValueSource(ints = { 10, 20, 30 })
    @DisplayName("int를 객체에 주입하여 객체로 전달 받기")
    void paramIntTest( @ConvertWith(SinglePersonConverter.class) Person person ) {
        System.out.println( person.getWeight() );
    }

    static class SinglePersonConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert( Object source, Class<?> targetType ) throws ArgumentConversionException {
            assertEquals( Person.class, targetType, () -> "Can only convert to Person" );
            Person person = new Person( "Kim", "1984.11.05" );
            person.setWeight( Integer.parseInt( source.toString() ) );
            return person;
        }
    }

    @ParameterizedTest(name = "{displayName}: ({index})")
    @CsvSource({ "'Kim', '1984'", "'Lee', '1985'", "'Park', '1986'" })
    @DisplayName("CSV Source 사용하기 - params")
    void paramsFromCsvSrcTest( String name, String birth ) {
        Person person = new Person( name, birth );
        System.out.println( person.getName() + " / " + person.getBirth() );
    }

    @ParameterizedTest(name = "{displayName}: ({index})")
    @CsvSource({ "'Kim', '1984'", "'Lee', '1985'", "'Park', '1986'" })
    @DisplayName("CSV Source 사용하기 - ArgumentsAccessor")
    void argumentsAccessorFromCsvSrcTest( ArgumentsAccessor argumentsAccessor ) {
        Person person = new Person( argumentsAccessor.getString( 0 )
                , argumentsAccessor.getString( 1 ) );
        System.out.println( person.getName() + " / " + person.getBirth() );
    }

    @ParameterizedTest(name = "{displayName}: ({index})")
    @CsvSource({ "'Kim', '1984'", "'Lee', '1985'", "'Park', '1986'" })
    @DisplayName("CSV Source 사용하기 - Object")
    void objectFromCsvSrcTest( @AggregateWith(PersonConverter.class) Person person ) {
        System.out.println( person.getName() + " / " + person.getBirth() );
    }

    static class PersonConverter implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments( ArgumentsAccessor accessor, ParameterContext context ) throws ArgumentsAggregationException {
            return new Person( accessor.getString( 0 ), accessor.getString( 1 ) );
        }
    }

    @ParameterizedTest(name = "{displayName}: ({index})")
    @CsvFileSource(files = "src/test/resources/data.csv")
    @DisplayName("CSV File Source 사용하기")
    void argumentsAccessorFromCsvFileSrcTest( ArgumentsAccessor argumentsAccessor ) {
        System.out.println( argumentsAccessor.getString( 0 ) + " / " + argumentsAccessor.getString( 1 ) );
    }

    @ParameterizedTest(name = "{displayName}: ({index})")
    @EnumSource(value = Months.class, mode = EnumSource.Mode.EXCLUDE, names = "JAN")
    void enumTest( Months months ) {
        System.out.println( months );
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    void testWithExplicitLocalMethodSource( String argument, int sequence ) {
        System.out.println( sequence + " " + argument );
        assertNotNull( argument );
    }

    static Stream<Arguments> argumentProvider() {
        return Stream.of(
                Arguments.arguments( "apple", 1 ),
                Arguments.arguments( "banana", 2 ) );
    }

    @ParameterizedTest
    @ArgumentsSource( CustomArgsProvider.class )
    void testWithCustomArgsProvider( String args ) {
        System.out.println( args );
    }

    static class CustomArgsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments( ExtensionContext context ) throws Exception {
            return Stream.of( "apple", "banana" ).map( Arguments::of );
        }
    }
}
