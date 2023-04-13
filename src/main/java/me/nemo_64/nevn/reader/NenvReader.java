package me.nemo_64.nevn.reader;

import me.nemo_64.nevn.NevnReaderException;

import java.util.Map;

@FunctionalInterface
public interface NenvReader {

    Map<String, String> read() throws NevnReaderException;

}