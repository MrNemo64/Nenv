package me.nemo_64.nevn.reader;

public class NenvReaderException extends Exception {

    public NenvReaderException() {
    }

    public NenvReaderException(String message) {
        super(message);
    }

    public NenvReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NenvReaderException(Throwable cause) {
        super(cause);
    }

    public NenvReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
