package com.stackroute.exception;

import lombok.Data;
/**
 * This is to give a template class to passback
 * error message to the user in case Error occurs
 */
@Data//Implicitly adding getters and setters
public class ErrorClass {

    private String errormessage;//This is message that is sent to client in case error occurence

    private String requestedURI;//This passes back the url for which the error has occured
}
