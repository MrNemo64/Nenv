package me.nemo_64.nevn.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NenvSystemEnvironmentReaderTest {

    @Test
    public void readTest() {
        NenvSystemEnvironmentReader reader = new NenvSystemEnvironmentReader();
        Assertions.assertEquals(System.getenv(), reader.read());
    }

}