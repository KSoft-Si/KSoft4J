package net.explodingbush.ksoftapi.exceptions;

public class FullException extends RuntimeException {

    /**
    * The exception that is thrown
     * @param reason
     * Reason for the exception to be thrown
     */
    public FullException(String reason) {
        super(reason);
    }
}
