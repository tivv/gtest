package gtest.controllers;

import gtest.exceptions.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vitalii Tymchyshyn
 */
public abstract class ExceptionHandlingController {
    @ExceptionHandler()
    public @ResponseBody Exception handleException(Exception e, HttpServletResponse response) throws IOException {
        if (e instanceof ControllerException) {
            ControllerException ce = (ControllerException) e;
            response.setStatus(ce.getStatus().value());
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return e;
    }
}
