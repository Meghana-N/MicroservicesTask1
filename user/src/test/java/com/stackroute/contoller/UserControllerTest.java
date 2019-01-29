package com.stackroute.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.User;
import com.stackroute.exception.UserNotFoundEXception;
import com.stackroute.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    User user;

    @MockBean
    UserService userService;

    @InjectMocks
    UserController userController;

    List<User> list;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
        user= new User(35,"amrit","this is password");
        list=new ArrayList<User>();
        list.add(user);
    }

    @After
    public void tearDown() throws Exception {
        user=null;
    }

    @Test
    public void insertTrackSuccess() throws Exception {
        when(userService.saveTheUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/music/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void insertTrackFailure() throws Exception{
        when(userService.saveTheUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/music/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTracksSuccess() throws Exception {
        when(userService.returnAllUsers()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/music/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getAllTracksFailure() throws Exception {
        when(userService.returnAllUsers()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.post("/music/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTracksSuccess() throws Exception {
        when(userService.updatePassword(anyInt(),anyString())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/music/user/?userId=35&password=this is comment")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTrackFailure() throws Exception {
        when(userService.updatePassword(anyInt(),anyString())).thenThrow(UserNotFoundEXception.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/music/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrackSuccess() throws Exception {
        when(userService.deletUser(anyInt())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/music/track/?trackid=35")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteTrackFailure() throws Exception{
        when(userService.deletUser(anyInt())).thenThrow(UserNotFoundEXception.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/music/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
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