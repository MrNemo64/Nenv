package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.BooleanEnvironmentEntry;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static me.nemo_64.nevn.entry.BooleanEnvironmentEntry.of;

class BooleanNenvParserTest {

    private static final String KEY = "key";

    @ParameterizedTest(name = "{index}: {0} => {1}")
    @MethodSource("testTryParseBooleanSource")
    public void testTryParseBoolean(String value, BooleanEnvironmentEntry expected) {
        BooleanNenvParser parser = BooleanNenvParser.INSTANCE;
        assertEquals(Optional.ofNullable(expected), parser.tryParse(KEY, value));
    }

    static Stream<Arguments> testTryParseBooleanSource() {
        return Stream.of(
                Arguments.of("true", of(true)),
                Arguments.of("false", of(false)),
                Arguments.of("TRUE", of(true)),
                Arguments.of("FALSE", of(false)),
                Arguments.of("", null),
                Arguments.of("no boolean", null)
        );
    }
}