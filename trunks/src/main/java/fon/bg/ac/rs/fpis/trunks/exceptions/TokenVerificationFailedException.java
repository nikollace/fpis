package fon.bg.ac.rs.fpis.trunks.exceptions;

public class TokenVerificationFailedException extends RuntimeException {

    public TokenVerificationFailedException() {
    }

    public TokenVerificationFailedException(String message) {
        super(message);
    }

    public TokenVerificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
