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
                Arguments.of("1", entry(1)),
                Arguments.of("1.0", entry(1D)),
                Arguments.of("-1", entry(-1)),
                Arguments.of("-.1", entry(-.1)),
                Arguments.of(String.valueOf(((long) Integer.MAX_VALUE) + 1), entry(((long) Integer.MAX_VALUE) + 1)),
                Arguments.of(bigIntegerTestCase.toString(), entry(bigIntegerTestCase)),
                Arguments.of(bigDecimalTestCase.toString(), entry(bigDecimalTestCase)),
                Arguments.of("NaN", entry(Double.NaN)),
                Arguments.of("", null),
                Arguments.of("no number", null)
        );
    }

    private static NumericalEnvironmentEntry entry(Number value) {
        return NumericalEnvironmentEntry.of(value);
    }

}