package gtest.exceptions;

import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @author Vitalii Tymchyshyn
 */
public class ControllerException extends IOException{
    private final HttpStatus status;

    public ControllerException(HttpStatus status) {
        this(status, status.getReasonPhrase());
    }

    public ControllerException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ControllerException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public ControllerException(HttpStatus status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
