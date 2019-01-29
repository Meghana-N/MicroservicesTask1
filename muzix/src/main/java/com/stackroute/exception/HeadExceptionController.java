package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;

/**This is to make the global exception handelre
 * it handles both Tracknotfound and TrackAlready exists exception
 */

@ControllerAdvice//This is to make this class the controller advice
public class HeadExceptionController {


    @ExceptionHandler(TrackNotFoundExeption.class)//this gives the exception which the method handles
    @ResponseStatus(value = HttpStatus.NOT_FOUND)  //this is HttpStatus for which the exception is handles
    public @ResponseBody ErrorClass handleTrackNotFound(final TrackNotFoundExeption e,final HttpServletRequest request){
        ErrorClass errorClass= new ErrorClass();
        errorClass.setErrormessage(e.getMessage());
        errorClass.setRequestedURI(request.getRequestURI());
        return errorClass;  //This is rendered to the user to view
    }


    @ExceptionHandler(TrackAlreadyExistsException.class)//this gives the exception which the method handles
    @ResponseStatus(value = HttpStatus.CONFLICT)        //this is HttpStatus for which the exception is handles
    public @ResponseBody ErrorClass handleTrackAlreadyExists(final TrackAlreadyExistsException e,final HttpServletRequest request){
        ErrorClass errorClass= new ErrorClass();
        errorClass.setErrormessage(e.getMessage());
        errorClass.setRequestedURI(request.getRequestURI());
        return errorClass; //This is rendered to the user to view
    }
}
