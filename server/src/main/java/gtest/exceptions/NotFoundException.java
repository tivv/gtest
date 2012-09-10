package gtest.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Vitalii Tymchyshyn
 */
public class NotFoundException extends ControllerException{
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(HttpStatus.NOT_FOUND, message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(HttpStatus.NOT_FOUND, cause);
    }
}
