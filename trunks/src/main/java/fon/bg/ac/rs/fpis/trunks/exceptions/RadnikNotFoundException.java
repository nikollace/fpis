package fon.bg.ac.rs.fpis.trunks.exceptions;

public class RadnikNotFoundException extends RuntimeException {

    public RadnikNotFoundException() {
    }

    public RadnikNotFoundException(String message) {
        super(message);
    }

    public RadnikNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
