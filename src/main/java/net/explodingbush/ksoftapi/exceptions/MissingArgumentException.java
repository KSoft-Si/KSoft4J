package net.explodingbush.ksoftapi.exceptions;

public class MissingArgumentException extends RuntimeException {

    /**
     * The default value if none is declared
     */
    public MissingArgumentException() {
        this("Unavailable");
    }
    /**
     * The exception that is thrown
     * @param reason
     * Reason for the exception to be thrown
     */
    public MissingArgumentException(String reason) {
        super(reason);
    }

}
