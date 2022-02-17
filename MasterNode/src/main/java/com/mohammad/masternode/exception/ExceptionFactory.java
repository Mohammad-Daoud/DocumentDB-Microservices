package com.mohammad.masternode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionFactory extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handAllExceptions(Exception ex, WebRequest request)  {
          ExceptionResponse exception =
                  new ExceptionResponse(new Date(), ex.toString(), request.getDescription(true));
       return new ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request)  {
        ExceptionResponse NotFoundException =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(NotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidJSONException.class)
    public final ResponseEntity<Object> handleInvalidJson(Exception ex, WebRequest request)  {
        ExceptionResponse exception =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserConflictException.class)
    public final ResponseEntity<Object> handleUserConflict(Exception ex, WebRequest request)  {
        ExceptionResponse exception =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exception, HttpStatus.CONFLICT);
    }
}
