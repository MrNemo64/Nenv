package me.nemo_64.nevn;

import me.nemo_64.nevn.entry.BooleanEnvironmentEntry;
import me.nemo_64.nevn.entry.CharacterEnvironmentEntry;
import me.nemo_64.nevn.entry.NumericalEnvironmentEntry;
import me.nemo_64.nevn.entry.StringEnvironmentEntry;
import me.nemo_64.nevn.parse.BooleanNenvParser;
import me.nemo_64.nevn.parse.CharacterNenvParser;
import me.nemo_64.nevn.parse.NenvParser;
import me.nemo_64.nevn.parse.NumberNenvParser;
import me.nemo_64.nevn.parse.StringNenvParser;
import me.nemo_64.nevn.reader.NenvReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NenvBuilder {

    private final List<NenvReader> readers = new ArrayList<>();
    private final List<NenvParser<?>> parsers = new ArrayList<>();
    private final Map<String, EnvironmentEntry<?>> entries = new HashMap<>();
    private Nenv fallback;
    private boolean throwOnException = false;

    protected NenvBuilder() {
        parsers.add(BooleanNenvParser.INSTANCE);
        parsers.add(NumberNenvParser.INSTANCE);
        parsers.add(CharacterNenvParser.INSTANCE);
        parsers.add(StringNenvParser.INSTANCE);
    }

    public NenvBuilder withEntry(String key, EnvironmentEntry<?> entry) {
        entries.put(Objects.requireNonNull(key, "key"), Objects.requireNonNull(entry, "entry"));
        return this;
    }

    public NenvBuilder withEntry(String key, boolean value) {
        return withEntry(key, new BooleanEnvironmentEntry(key, value));
    }

    public NenvBuilder withEntry(String key, Number value) {
        return withEntry(key, new NumericalEnvironmentEntry(key, value));
    }

    public NenvBuilder withEntry(String key, String value) {
        return withEntry(key, new StringEnvironmentEntry(key, value));
    }

    public NenvBuilder withEntry(String key, char value) {
        return withEntry(key, new CharacterEnvironmentEntry(key, value));
    }

    public NenvBuilder clearParsers() {
        parsers.clear();
        return this;
    }

    public NenvBuilder addParser(int position, NenvParser<?> parser) {
        parsers.add(position, Objects.requireNonNull(parser, "parser"));
        return this;
    }

    public NenvBuilder addParser(NenvParser<?> parser) {
        parsers.add(Objects.requireNonNull(parser, "parser"));
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
                lines.putAll(reader.read());
            } catch (NevnReaderException e) {
                if(throwOnException)
                    throw new NenvBuildException(e);
            }
        }
        for(Map.Entry<String, String> entry : lines.entrySet()) {
            Optional<? extends EnvironmentEntry<?>> value = parse(entry.getKey(), entry.getValue());
            value.ifPresent(environmentEntry -> entries.put(entry.getKey(), environmentEntry));
            // TODO what if value::isEmpty
        }
        return new Nenv(entries, fallback);
    }

    private Optional<? extends EnvironmentEntry<?>> parse(String key, String value) {
        for (NenvParser<?> parser : parsers) {
            Optional<? extends EnvironmentEntry<?>> entry = parser.tryParse(key, value);
            if(entry.isPresent())
                return entry;
        }
        return Optional.empty();
    }

}
