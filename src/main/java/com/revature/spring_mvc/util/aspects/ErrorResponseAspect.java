package com.revature.spring_mvc.util.aspects;


import com.revature.spring_mvc.exceptions.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // this combines @Aspect and @Component for controllers
public class ErrorResponseAspect {

    @ExceptionHandler({ InvalidRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleInvalidExceptions(Exception e) {
        System.out.println("Caught invalid Request Exception: " + e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void runtimeException(Exception e){
        System.out.println("Caught runtimeException: " + e.getMessage());
    }

}
