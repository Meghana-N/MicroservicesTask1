package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundEXception;

import java.util.List;

/**This is the service layer where the various methods are
 * mentioned which needs to be overriden in th e implementation
 */

public interface UserService {

    public User saveTheUser(User user) throws UserAlreadyExistsException;

    public List<User> returnAllUsers() throws UserNotFoundEXception;

    public User updatePassword(int userId,String newpassword) throws UserNotFoundEXception;

    public List<User> deletUser(int userId) throws UserNotFoundEXception;
}
