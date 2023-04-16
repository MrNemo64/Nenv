package me.nemo_64.nevn.reader;

import java.util.Map;

@FunctionalInterface
public interface NenvReader {

    Map<String, String> read() throws NenvReaderException;

}