package me.nemo_64.nevn.reader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NenvFileReaderTest {

    @DisplayName("Test split")
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