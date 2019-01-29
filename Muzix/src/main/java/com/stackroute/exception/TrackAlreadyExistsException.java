package com.stackroute.exception;

/**This class extends Exception and is a
 * custom exception handler that handeles exception if the
 * track already exists
 */
public class TrackAlreadyExistsException extends Exception {

    String message;//This is message that is showed

    public TrackAlreadyExistsException(){
    }

    public TrackAlreadyExistsException(String message){
        super(message);//Setting message to string value thrown by Service layer while throwing exception
    }
}
