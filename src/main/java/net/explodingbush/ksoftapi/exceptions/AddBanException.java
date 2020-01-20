package net.explodingbush.ksoftapi.exceptions;

public class AddBanException extends RuntimeException {

    /**
     * The default value if none is declared
     */
    public AddBanException() {
        this("Unavailable");
    }
    /**
     * The exception that is thrown
     * @param reason
     * Reason for the exception to be thrown
     */
    public AddBanException(String reason) {
        super(reason);
    }

}
