package net.explodingbush.ksoftapi.exceptions;

public class AlreadyExistsException extends RuntimeException {

    /**
    * The exception that is thrown
     * @param reason
     * Reason for the exception to be thrown
     */
    public AlreadyExistsException(String reason) {
        super(reason);
    }
}
