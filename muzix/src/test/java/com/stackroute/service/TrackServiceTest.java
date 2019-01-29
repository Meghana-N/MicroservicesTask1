package com.stackroute.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundExeption;
import com.stackroute.repository.TrackRepository;
import net.minidev.json.writer.CollectionMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    Track track;
    Track track2;

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackService;

    List<Track> list;
    List<Track> list2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        track=new Track(45,"Track from service layer","for testing");
        track2=new Track(44,"Track from service layer","for testing");
        list=new ArrayList<Track>();
        list2=new ArrayList<Track>();
        trackRepository.save(track2);
        list.add(track);
    }

    @After
    public void tearDown() throws Exception {
        track=null;
    }

    @Test
    public void testSaveTrackSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track tracktest=trackService.saveTheTrack(track);
        Assert.assertEquals(track,tracktest);
        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void testSaveTrackFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(track);
        when(trackRepository.existsById(track.getTrackid())).thenReturn(true);
        Track track1=trackService.saveTheTrack(track);
        Assert.assertEquals(track,track1);
    }

    @Test
    public void testFindAllTracksSuccess() throws TrackNotFoundExeption {
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> test=trackService.returnAllTracks();
        Assert.assertEquals(list,test);
        verify(trackRepository,times(1)).findAll();
    }

    @Test(expected = TrackNotFoundExeption.class)
    public void testFindAllTracksFailure() throws TrackNotFoundExeption {
        when(trackRepository.findAll()).thenReturn(list2);
        when(trackRepository.existsById(1)).thenReturn(false);
        List<Track> test=trackService.returnAllTracks();
        Assert.assertEquals(list,test);
    }

    @Test(expected = TrackNotFoundExeption.class)
    public void testDeleteTrackSuccess() throws TrackNotFoundExeption {
        trackService.deletTrack(track2.getTrackid());
        verify(trackRepository,times(1)).deleteById(track2.getTrackid());
    }

    @Test(expected = TrackNotFoundExeption.class)
    public void testDeleteTrackFailure() throws TrackNotFoundExeption {
        when(trackRepository.existsById(track.getTrackid())).thenReturn(false);
        trackService.deletTrack(track.getTrackid());
    }

    @Test
    public void testUpdateTracksSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track tracktest=trackService.saveTheTrack(track);
        Assert.assertEquals(track,tracktest);
    }

    @Test(expected = TrackNotFoundExeption.class)
    public void testUpdateTracksFailure() throws TrackNotFoundExeption {
        when(trackRepository.save((Track)any())).thenReturn(track);
        when(trackRepository.existsById(track.getTrackid())).thenReturn(false);
        Track track1= trackService.updateTrack(track.getTrackid(),track.getComments());
        Assert.assertEquals(track,track1);
    }

}