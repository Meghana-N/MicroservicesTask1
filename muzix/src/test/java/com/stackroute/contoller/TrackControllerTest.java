package com.stackroute.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundExeption;
import com.stackroute.service.TrackService;
import com.stackroute.service.TrackServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    @Autowired
    MockMvc mockMvc;

    Track track;

    @MockBean
    TrackService trackService;

    @InjectMocks
    TrackController trackController;

    List<Track> list;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(trackController).build();
        track= new Track(35,"this is name","this is comment");
        list=new ArrayList<Track>();
        list.add(track);
    }

    @After
    public void tearDown() throws Exception {
        track=null;
    }

    @Test
    public void insertTrackSuccess() throws Exception {
        when(trackService.saveTheTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/music/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void insertTrackFailure() throws Exception{
        when(trackService.saveTheTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/music/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTracksSuccess() throws Exception {
        when(trackService.returnAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/music/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getAllTracksFailure() throws Exception {
        when(trackService.returnAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.post("/music/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTracksSuccess() throws Exception {
        when(trackService.updateTrack(anyInt(),anyString())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.put("/music/track/?trackid=35&comments=this is comment")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTrackFailure() throws Exception {
        when(trackService.updateTrack(anyInt(),anyString())).thenThrow(TrackNotFoundExeption.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/music/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrackSuccess() throws Exception {
        when(trackService.deletTrack(anyInt())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/music/track/?trackid=35")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteTrackFailure() throws Exception{
        when(trackService.deletTrack(anyInt())).thenThrow(TrackNotFoundExeption.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/music/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

    }



    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}