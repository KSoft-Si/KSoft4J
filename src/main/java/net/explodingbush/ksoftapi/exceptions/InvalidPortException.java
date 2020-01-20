package net.explodingbush.ksoftapi.exceptions;

public class InvalidPortException extends RuntimeException {

    /**
     * The exception that is thrown if the token is incorrect or not provided
     */

    public InvalidPortException() {
        this("The provided port is invalid. Using a default port");
    }
    public InvalidPortException(String reason) {
        super(reason);
    }

}
