package com.ManagmentTask.Task.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFound e)
    {
           ErrorResponse error= new ErrorResponse(
                   HttpStatus.NOT_FOUND.value(),
                   e.getMessage(),
                   LocalDateTime.now()
           );
           return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(TaskNotFound ex)
    {
          ErrorResponse error=new ErrorResponse(
                  HttpStatus.NOT_FOUND.value(),
                  ex.getMessage(),
                  LocalDateTime.now()
          );
          return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ErrorResponse>handleInternalException(InternalException ex)
    {
         ErrorResponse error=new ErrorResponse(
                 HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 ex.getMessage(),
                 LocalDateTime.now()
         );
         return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DublicateUser.class)
    public ResponseEntity<ErrorResponse> handleDublicateUserName(DublicateUser ex)
    {
         ErrorResponse errorResponse=new ErrorResponse(
                  HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 ex.getMessage(),
                 LocalDateTime.now()
         );
         return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(MultiTaskAssigned.class)
    public ResponseEntity<ErrorResponse> handleMultiTaskAssigned(MultiTaskAssigned ex)
    {
         ErrorResponse errorResponse=new ErrorResponse(
                  HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 ex.getMessage(),
                 LocalDateTime.now()
         );
         return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
