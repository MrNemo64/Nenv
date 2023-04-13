package me.nemo_64.nevn;

public class NevnReaderException extends Exception {

    public NevnReaderException() {
    }

    public NevnReaderException(String message) {
        super(message);
    }

    public NevnReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NevnReaderException(Throwable cause) {
        super(cause);
    }

    public NevnReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
