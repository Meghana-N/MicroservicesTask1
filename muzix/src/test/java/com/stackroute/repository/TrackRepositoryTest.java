package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest // or use @SpringBootTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp() throws Exception {
        track=new Track();
        track.setTrackid(20);
        track.setTrackname("youth");
        track.setComments("by shawn mendes");
    }

    @After
    public void tearDown() throws Exception {
        trackRepository=null;
    }

    @Test
    public void testSaveUserSuccess(){
        Track tracktest= new Track(20,"youth","by shawn mendes");
        Track test=trackRepository.save(tracktest);
        Assert.assertEquals(test,track);
    }

    @Test
    public void testUserSaveFailure(){
        Track tracktest= new Track(30,"youth","by shawn mendes");
        Track test=trackRepository.save(tracktest);
        Assert.assertNotEquals(test,track);
    }

    @Test
    public void testExistsByIdSuccess(){
        trackRepository.save(new Track(40,"trackname","trackcommenrs"));
        Boolean answer=trackRepository.existsById(40);
        Assert.assertEquals(true,answer);
    }

    @Test
    public void testExistsByIdFailure(){
        Boolean answer=trackRepository.existsById(55);
        Assert.assertNotEquals(true,answer);
    }

    @Test
    public void testFindAllSuccess(){
        Assert.assertEquals(20,trackRepository.findAll().get(0).getTrackid());
    }

    @Test
    public void testFindAllFailure(){
     Assert.assertNotEquals(6,trackRepository.findAll().get(0).getTrackid());
    }

    @Test
    public void testFindByIdSuccess(){
        Track tracktest1=trackRepository.findById(20).get();
        Assert.assertEquals(track,tracktest1);
    }

    @Test
    public void testFindByIdFailure(){
        Track tracktest=trackRepository.findById(40).get();
        Assert.assertNotEquals(track,tracktest);
    }

    @Test
    public void testDeleteByIdSuccess(){
        trackRepository.deleteById(40);
        Boolean answer=trackRepository.existsById(40);
        Assert.assertEquals(false,answer);
    }

    @Test
    public void testDeletebyFailure(){
        Boolean answer=trackRepository.existsById(20);
        Assert.assertNotEquals(false,answer);
    }

}