package me.nemo_64.nevn.reader;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

public class NenvFileReaderBuilder {

    private Path file = Path.of(".env");
    private Charset charset = StandardCharsets.UTF_8;
    private char splitter = '=';
    private boolean throwIfMalformed = false;

    public NenvFileReaderBuilder throwIfMalformed() {
        return throwIfMalformed(true);
    }

    public NenvFileReaderBuilder throwIfMalformed(boolean throwIfMalformed) {
        this.throwIfMalformed = throwIfMalformed;
        return this;
    }

    public NenvFileReaderBuilder withSplitter(char splitter) {
        this.splitter = splitter;
        return this;
    }

    public NenvFileReaderBuilder withCharset(Charset charset) {
        this.charset = Objects.requireNonNull(charset, "charset");
        return this;
    }

    public NenvFileReaderBuilder withFile(Path file) {
        this.file = Objects.requireNonNull(file, "file");
        return this;
    }

    public NenvFileReader create() {
        return new NenvFileReader(file, charset, splitter, throwIfMalformed);
    }

}
