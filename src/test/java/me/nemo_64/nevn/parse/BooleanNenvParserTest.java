package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.BooleanEnvironmentEntry;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BooleanNenvParserTest {

    private static final String KEY = "key";

    @ParameterizedTest(name = "{index}: {0} => {1}")
    @MethodSource("testTryParseBooleanSource")
    public void testTryParseBoolean(String value, BooleanEnvironmentEntry expected) {
        BooleanNenvParser parser = new BooleanNenvParser();
        assertEquals(Optional.ofNullable(expected), parser.tryParse(KEY, value));
    }

    static Stream<Arguments> testTryParseBooleanSource() {
        return Stream.of(
                Arguments.of("true", entry(true)),
                Arguments.of("false", entry(false)),
                Arguments.of("TRUE", entry(true)),
                Arguments.of("FALSE", entry(false)),
                Arguments.of("", null),
                Arguments.of("no boolean", null)
        );
    }

    private static BooleanEnvironmentEntry entry(boolean value) {
        return new BooleanEnvironmentEntry(KEY, value);
    }

}