/*
package com.stackroute.repository;

import com.stackroute.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest // or use @SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    User user;

    @Before
    public void setUp() throws Exception {
        user =new User();
        user.setUserid(20);
        user.setPassword("youth");
        user.setEmaild("by shawn mendes");
    }

    @After
    public void tearDown() throws Exception {
        userRepository =null;
    }

    @Test
    public void testSaveUserSuccess(){
        User usertest= new User(20,"youth","by shawn mendes");
        User test= userRepository.save(usertest);
        Assert.assertEquals(test, this.user);
    }

    @Test
    public void testUserSaveFailure(){
        User usertest= new User(30,"youth","by shawn mendes");
        User test= userRepository.save(usertest);
        Assert.assertNotEquals(test, user);
    }

    @Test
    public void testExistsByIdSuccess(){
        userRepository.save(new User(40,"trackname","trackcommenrs"));
        Boolean answer= userRepository.existsById(40);
        Assert.assertEquals(true,answer);
    }

    @Test
    public void testExistsByIdFailure(){
        Boolean answer= userRepository.existsById(55);
        Assert.assertNotEquals(true,answer);
    }

    @Test
    public void testFindAllSuccess(){
        Assert.assertEquals(20, userRepository.findAll().get(0).getUserid());
    }

    @Test
    public void testFindAllFailure(){
     Assert.assertNotEquals(6, userRepository.findAll().get(0).getUserid());
    }

    @Test
    public void testFindByIdSuccess(){
        User usertest1= userRepository.findById(20).get();
        Assert.assertEquals(user,usertest1);
    }

    @Test
    public void testFindByIdFailure(){
        User usertest= userRepository.findById(40).get();
        Assert.assertNotEquals(user,usertest);
    }

    @Test
    public void testDeleteByIdSuccess(){
        userRepository.deleteById(40);
        Boolean answer= userRepository.existsById(40);
        Assert.assertEquals(false,answer);
    }

    @Test
    public void testDeletebyFailure(){
        Boolean answer= userRepository.existsById(20);
        Assert.assertNotEquals(false,answer);
    }

}
*/
