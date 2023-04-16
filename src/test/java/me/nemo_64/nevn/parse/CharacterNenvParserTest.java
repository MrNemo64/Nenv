package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.CharacterEnvironmentEntry;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CharacterNenvParserTest {

    private static final String KEY = "KEY";

    @ParameterizedTest(name = "{index}: {0} => {1}")
    @MethodSource("testTryParseCharSource")
    public void testTryParseChar(String value, CharacterEnvironmentEntry expected) {
        CharacterNenvParser parser = CharacterNenvParser.INSTANCE;
        assertEquals(Optional.ofNullable(expected), parser.tryParse(KEY, value));
    }

    static Stream<Arguments> testTryParseCharSource() {
        return Stream.of(
                Arguments.of("c", entry('c')),
                Arguments.of("C", entry('C')),
                Arguments.of("À", entry('À')),
                Arguments.of("à", entry('à')),
                Arguments.of("1", entry('1')),
                Arguments.of("", null),
                Arguments.of("no char", null)
        );
    }

    private static CharacterEnvironmentEntry entry(char value) {
        return CharacterEnvironmentEntry.of(value);
    }

}