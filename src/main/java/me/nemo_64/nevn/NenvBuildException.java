package me.nemo_64.nevn;

public class NenvBuildException extends RuntimeException {
    public NenvBuildException() {
    }

    public NenvBuildException(String message) {
        super(message);
    }

    public NenvBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public NenvBuildException(Throwable cause) {
        super(cause);
    }

    public NenvBuildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
