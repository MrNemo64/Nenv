package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.NumericalEnvironmentEntry;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberNenvParserTest {

    private static final String KEY = "KEY";

    @ParameterizedTest(name = "{index}: {0} => {1}")
    @MethodSource("testTryParseNumberSource")
    public void testTryParseNumber(String value, NumericalEnvironmentEntry expected) {
        NumberNenvParser parser = NumberNenvParser.INSTANCE;
        assertEquals(Optional.ofNullable(expected), parser.tryParse(KEY, value));
    }

    static Stream<Arguments> testTryParseNumberSource() {
        BigInteger bigIntegerTestCase = BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE);
        BigDecimal bigDecimalTestCase = BigDecimal.valueOf(Double.MAX_VALUE).add(BigDecimal.valueOf(.1));
        return Stream.of(
                Arguments.of("1", entry((byte)1)),
                Arguments.of("-1", entry((byte)-1)),

                Arguments.of(String.valueOf(Byte.MIN_VALUE), entry(Byte.MIN_VALUE)),
                Arguments.of(String.valueOf(Byte.MIN_VALUE - 1), entry((short)(Byte.MIN_VALUE - 1))),
                Arguments.of(String.valueOf(Byte.MAX_VALUE), entry(Byte.MAX_VALUE)),
                Arguments.of(String.valueOf(Byte.MAX_VALUE + 1), entry((short)(Byte.MAX_VALUE + 1))),

                Arguments.of(String.valueOf(Short.MIN_VALUE), entry(Short.MIN_VALUE)),
                Arguments.of(String.valueOf(Short.MIN_VALUE - 1), entry(Short.MIN_VALUE - 1)),
                Arguments.of(String.valueOf(Short.MAX_VALUE), entry(Short.MAX_VALUE)),
                Arguments.of(String.valueOf(Short.MAX_VALUE + 1), entry(Short.MAX_VALUE + 1)),

                Arguments.of(String.valueOf(Integer.MIN_VALUE), entry(Integer.MIN_VALUE)),
                Arguments.of(String.valueOf(Integer.MIN_VALUE - 1L), entry(Integer.MIN_VALUE - 1L)),
                Arguments.of(String.valueOf(Integer.MAX_VALUE), entry(Integer.MAX_VALUE)),
                Arguments.of(String.valueOf(Integer.MAX_VALUE + 1L), entry(Integer.MAX_VALUE + 1L)),

                Arguments.of("1.0", entry(1F)),
                Arguments.of("-.1", entry(-.1F)),
                Arguments.of(String.valueOf(((long) Integer.MAX_VALUE) + 1), entry(((long) Integer.MAX_VALUE) + 1)),
                Arguments.of(bigIntegerTestCase.toString(), entry(bigIntegerTestCase)),
                Arguments.of(bigDecimalTestCase.toString(), entry(bigDecimalTestCase)),
                Arguments.of("", null),
                Arguments.of("no number", null)
        );
    }

    private static NumericalEnvironmentEntry entry(Number value) {
        return NumericalEnvironmentEntry.of(value);
    }

}