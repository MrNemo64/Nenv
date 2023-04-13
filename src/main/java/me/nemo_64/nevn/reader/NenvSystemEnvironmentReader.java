package me.nemo_64.nevn.reader;

import java.util.Map;

public class NenvSystemEnvironmentReader implements NenvReader {
    @Override
    public Map<String, String> read() {
        return System.getenv();
    }
}
