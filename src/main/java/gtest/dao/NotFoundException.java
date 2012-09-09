package gtest.dao;

/**
 * @author Vitalii Tymchyshyn
 */
public class NotFoundException extends DaoException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
