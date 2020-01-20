package net.explodingbush.ksoftapi.exceptions;

public class LoginException extends RuntimeException {

    /**
     * The exception that is thrown if the token is incorrect or not provided
     */

    public LoginException() {
        this("The provided token is incorrect");
    }
    public LoginException(String reason) {
        super(reason);
    }

}
