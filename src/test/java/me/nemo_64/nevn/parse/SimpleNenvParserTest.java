package me.nemo_64.nevn.parse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleNenvParserTest {

    private SimpleNenvParser parser;

    @BeforeEach
    public void setUp() {
        parser = new SimpleNenvParser();
    }

    @ParameterizedTest(name = "{index}: {0} => {1}")
    @MethodSource("testTryParseNumberSource")
    public void tryParseNumber(String value, Number expected) {
        assertEquals(expected, parser.tryParseNumber(value));
    }

    @ParameterizedTest(name = "{index}: {0} => {1}")
    @MethodSource("testTryParseCharSource")
    public void testTryParseChar(String value, Character expected) {
        assertEquals(expected, parser.tryParseChar(value));
    }

    @ParameterizedTest(name = "{index}: {0} => {1}")
    @MethodSource("testTryParseBooleanSource")
    public void testTryParseBoolean(String value, Boolean expected) {
        assertEquals(expected, parser.tryParseBoolean(value));
    }

    static Stream<Arguments> testTryParseNumberSource() {
        BigInteger bigIntegerTestCase = BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE);
        BigDecimal bigDecimalTestCase = BigDecimal.valueOf(Double.MAX_VALUE).add(BigDecimal.valueOf(.1));
        return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("1.0", 1D),
                Arguments.of("-1", -1),
                Arguments.of("-.1", -.1),
                Arguments.of(String.valueOf(((long) Integer.MAX_VALUE) + 1), ((long) Integer.MAX_VALUE) + 1),
                Arguments.of(bigIntegerTestCase.toString(), bigIntegerTestCase),
                Arguments.of(bigDecimalTestCase.toString(), bigDecimalTestCase),
                Arguments.of("NaN", Double.NaN),
                Arguments.of("", null),
                Arguments.of("no number", null)
        );
    }

    static Stream<Arguments> testTryParseCharSource() {
        return Stream.of(
                Arguments.of("c", 'c'),
                Arguments.of("C", 'C'),
                Arguments.of("À", 'À'),
                Arguments.of("à", 'à'),
                Arguments.of("1", '1'),
                Arguments.of("", null),
                Arguments.of("no char", null)
        );
    }

    static Stream<Arguments> testTryParseBooleanSource() {
        return Stream.of(
                Arguments.of("true", Boolean.TRUE),
                Arguments.of("false", Boolean.FALSE),
                Arguments.of("TRUE", Boolean.TRUE),
                Arguments.of("FALSE", Boolean.FALSE),
                Arguments.of("", null),
                Arguments.of("no boolean", null)
        );
    }

}