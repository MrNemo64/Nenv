package me.nemo_64.nevn.reader;

import me.nemo_64.nevn.NevnReaderException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NenvFileReader implements NenvReader {

    public static NenvFileReaderBuilder builder() {
        return new NenvFileReaderBuilder();
    }

    private final Path file;
    private final Charset charset;
    private final char splitter;
    private final boolean throwIfMalformed;

    protected final StringBuilder keyBuilder = new StringBuilder();
    protected final StringBuilder valueBuilder = new StringBuilder();

    public NenvFileReader(Path file, Charset charset, char splitter, boolean throwIfMalformed) {
        this.file = file;
        this.charset = charset;
        this.splitter = splitter;
        this.throwIfMalformed = throwIfMalformed;
    }

    @Override
    public Map<String, String> read() throws NevnReaderException {
        return parseLines(readLines());
    }

    protected List<String> readLines() throws NevnReaderException {
        try {
            return Files.readAllLines(file, charset);
        } catch (IOException e) {
            throw new NevnReaderException(e);
        }
    }

    protected Map<String, String> parseLines(List<String> lines) throws NevnReaderException {
        Map<String, String> entries = new HashMap<>();
        List<String> malformedLines = new ArrayList<>();
        boolean hasError = false;
        for(String line : lines) {
            if(!split(line)) {
                hasError = true;
                malformedLines.add(line);
                continue;
            }
            entries.put(keyBuilder.toString(), valueBuilder.toString());
        }
        if(hasError && throwIfMalformed) {
            throw new NevnReaderException(
                    String.format("Could not read file %s because it contains malformed lines: %n%s",
                            file,
                            String.join(String.format("%n"), malformedLines)));
        }
        return entries;
    }

    protected boolean split(String line) {
        keyBuilder.setLength(0);
        valueBuilder.setLength(0);
        char[] chars = line.toCharArray();
        int index = 0;
        for(; index < line.length(); index++) {
            char c = chars[index];
            if(c == splitter) {
                index++;
                break;
            }
            keyBuilder.append(c);
        }
        for(; index < line.length(); index++) {
            valueBuilder.append(chars[index]);
        }
        return keyBuilder.length() > 0 && valueBuilder.length() > 0;
    }

}
