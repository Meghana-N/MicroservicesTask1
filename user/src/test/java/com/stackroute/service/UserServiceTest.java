package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundEXception;
import com.stackroute.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    User user;
    User user2;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    List<User> list;
    List<User> list2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(45,"User from service layer","for testing");
        user2=new User(44,"User from service layer","for testing");
        list=new ArrayList<User>();
        list2=new ArrayList<User>();
        userRepository.save(user2);
        list.add(user);
    }

    @After
    public void tearDown() throws Exception {
        user=null;
    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistsException {
        when(userRepository.save((User)any())).thenReturn(user);
        User usertest=userService.saveTheUser(user);
        Assert.assertEquals(user,usertest);
        verify(userRepository,times(1)).save(user);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testSaveUserFailure() throws UserAlreadyExistsException {
        when(userRepository.save((User)any())).thenReturn(user);
        when(userRepository.existsById(user.getUserid())).thenReturn(true);
        User user1=userService.saveTheUser(user);
        Assert.assertEquals(user,user1);
    }

    @Test
    public void testFindAllUsersSuccess() throws UserNotFoundEXception {
        when(userRepository.findAll()).thenReturn(list);
        List<User> test=userService.returnAllUsers();
        Assert.assertEquals(list,test);
        verify(userRepository,times(1)).findAll();
    }

    @Test(expected = UserNotFoundEXception.class)
    public void testFindAllUsersFailure() throws UserNotFoundEXception {
        when(userRepository.findAll()).thenReturn(list2);
        when(userRepository.existsById(1)).thenReturn(false);
        List<User> test=userService.returnAllUsers();
        Assert.assertEquals(list,test);
    }

    @Test(expected = UserNotFoundEXception.class)
    public void testDeleteUserSuccess() throws UserNotFoundEXception {
        userService.deletUser(user2.getUserid());
        verify(userRepository,times(1)).deleteById(user2.getUserid());
    }

    @Test(expected = UserNotFoundEXception.class)
    public void testDeleteUserFailure() throws UserNotFoundEXception {
        when(userRepository.existsById(user.getUserid())).thenReturn(false);
        userService.deletUser(user.getUserid());
    }

    @Test
    public void testUpdateUsersSuccess() throws UserAlreadyExistsException {
        when(userRepository.save((User)any())).thenReturn(user);
        User usertest=userService.saveTheUser(user);
        Assert.assertEquals(user,usertest);
    }

    @Test(expected = UserNotFoundEXception.class)
    public void testUpdateUsersFailure() throws UserNotFoundEXception {
        when(userRepository.save((User)any())).thenReturn(user);
        when(userRepository.existsById(user.getUserid())).thenReturn(false);
        User user1= userService.updatePassword(user.getUserid(),user.getPassword());
        Assert.assertEquals(user,user1);
    }

}