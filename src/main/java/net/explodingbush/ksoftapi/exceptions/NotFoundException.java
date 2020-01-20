package net.explodingbush.ksoftapi.exceptions;

public class NotFoundException extends RuntimeException {

    /**
    * The exception that is thrown
     * @param reason
     * Reason for the exception to be thrown
     */
    public NotFoundException(String reason) {
        super(reason);
    }
}
