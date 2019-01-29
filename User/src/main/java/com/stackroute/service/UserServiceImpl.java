package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundEXception;
import com.stackroute.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**This class implements the track service interfacea
 * and implements the methods, this class uses the inbuilt methods of
 * JPA repository to perform CRUD operations
 */

@Service
public class UserServiceImpl implements UserService {
    //Making variable of trackrepository type
    private UserRepository userRepository;

    //This is setting the trackrepository variable by constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //This method saves the Track Object passed as parameter in the database
    @Override
    public User saveTheUser(User track) throws UserAlreadyExistsException {
        if (userRepository.existsById(track.getUserid())) {
            throw new UserAlreadyExistsException("user already exists");
        }
        userRepository.save(track);
        return track;
    }

    //This method returns all the tracks present in the Database
    @Override
    public List<User> returnAllUsers() throws UserNotFoundEXception {
        List<User> userlist = userRepository.findAll();
        if (userlist.isEmpty() || userlist == null) {
            throw new UserNotFoundEXception("Error while returning the users");
        }
        return userlist;
    }

    //This method updates an existing track with the new Track object passed by matching id
    @Override
    public User updatePassword(int userId,String password) throws UserNotFoundEXception {
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundEXception("error while changing password");
        }
        Optional<User> track=userRepository.findById(userId);
        User user1=track.get();
        user1.setPassword(password);
        userRepository.save(user1);
        return user1;
    }

    //This method is used to delete a track from database by mapping the entry using
    // track id passed as parameter
    @Override
    public List<User> deletUser(int userId) throws UserNotFoundEXception {
        if (userRepository.existsById(userId)==false) {
            throw new UserNotFoundEXception("Error in deleting User");
        }
        userRepository.deleteById(userId);
        return userRepository.findAll();
    }
}
