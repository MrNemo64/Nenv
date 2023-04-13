package me.nemo_64.nevn;

import me.nemo_64.nevn.parse.NenvParser;
import me.nemo_64.nevn.parse.SimpleNenvParser;
import me.nemo_64.nevn.reader.NenvReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NenvBuilder {

    private final List<NenvReader> readers = new ArrayList<>();
    private NenvParser parser = new SimpleNenvParser();
    private Nenv fallback;
    private boolean throwOnException = false;

    protected NenvBuilder() {}

    public NenvBuilder withParser(NenvParser parser) {
        this.parser = Objects.requireNonNull(parser, "parser");
        return this;
    }

    public NenvBuilder withFallback(Nenv fallback) {
        this.fallback = Objects.requireNonNull(fallback, "fallback");
        return this;
    }

    public NenvBuilder addReader(NenvReader reader) {
        this.readers.add(Objects.requireNonNull(reader, "reader"));
        return this;
    }

    public NenvBuilder throwOnException() {
        return throwOnException(true);
    }

    public NenvBuilder throwOnException(boolean throwOnException) {
        this.throwOnException = throwOnException;
        return this;
    }

    public Nenv create() throws NenvBuildException {
        Map<String, String> lines = new HashMap<>();
        for(NenvReader reader : readers) {
            try {
                Map<String, String> readed = reader.read();
                lines.putAll(readed);
            } catch (NevnReaderException e) {
                if(throwOnException)
                    throw new NenvBuildException(e);
            }
        }
        return new Nenv(parser.parse(lines), fallback);
    }

}
