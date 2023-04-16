package me.nemo_64.nevn.reader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NenvFileReaderTest {

    @Test
    public void testParseLinesNotThrowing() {
        final NenvFileReader nenvFileReader = NenvFileReader.builder().throwIfMalformed(false).create();

        final List<String> lines = List.of(
                "key1=val1",
                "key2=",
                "key3=val3",
                "=val4",
                "key5:val5",
                "=",
                "key6=val6"
        );
        final Map<String, String> expectedMap = Map.of(
                "key1", "val1",
                "key3", "val3",
                "key6", "val6"
        );

        Map<String, String> obtained = assertDoesNotThrow(() -> nenvFileReader.parseLines(lines));
        assertEquals(expectedMap, obtained);
    }

    @Test
    public void testParseLinesThrowing() {
        final NenvFileReader nenvFileReader = NenvFileReader.builder().throwIfMalformed().create();

        final List<String> lines = List.of(
                "key1=val1",
                "key2=",
                "key3=val3",
                "=val4",
                "key5:val5",
                "=",
                "key6=val6"
        );
        final String expectedMessage = String.format("Could not read file .env because it contains malformed lines:%n%s",
                String.join(String.format("%n"), List.of(
                "key2=",
                "=val4",
                "key5:val5",
                "="
        )));

        NenvReaderException obtainedException = assertThrows(NenvReaderException.class,
                () -> nenvFileReader.parseLines(lines));
        assertEquals(expectedMessage, obtainedException.getMessage());
    }

    @ParameterizedTest(name = "{index}: '{0}'/{4} => ({1}) '{2}'='{3}'")
    @MethodSource("testSplitArguments")
    public void testSplit(String line, boolean expectedReturn, String expectedKey, String expectedValue, char splitter) {
        NenvFileReader nenvFileReader = NenvFileReader.builder().withSplitter(splitter).create();

        assertEquals(expectedReturn, nenvFileReader.split(line));
        if(expectedReturn) {
            assertEquals(expectedKey, nenvFileReader.keyBuilder.toString());
            assertEquals(expectedValue, nenvFileReader.valueBuilder.toString());
        }
    }

    static Stream<Arguments> testSplitArguments() {
        return Stream.of(
                Arguments.of("key1=val1", true, "key1", "val1", '='),
                Arguments.of("key1:val1", true, "key1", "val1", ':'),
                Arguments.of("=val1", false, null, null, '='),
                Arguments.of("key1=", false, null, null, '='),
                Arguments.of("key1=val1", false, null, null, ':'),
                Arguments.of("", false, null, null, ':'),
                Arguments.of("=", false, null, null, '=')
        );
    }

}