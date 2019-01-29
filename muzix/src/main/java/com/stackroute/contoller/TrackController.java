package com.stackroute.contoller;

import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundExeption;
import com.stackroute.service.TrackService;
import com.stackroute.domain.Track;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("music")
@Api(value = "Musics App", description = "This is a music app implemented in REST")
public class TrackController {
    //Trackservice variable
    private TrackService trackService;

    //Set TrackService variable using trackcontroller constructor
    @Autowired
    public TrackController(TrackService trackService){
        this.trackService=trackService;
    }

    //Insert track into the database
    @ApiOperation(value = "Insert a track", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("track")
    public ResponseEntity<?> insertTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        Track track21=trackService.saveTheTrack(track);
        responseEntity=new ResponseEntity<String>("Success-track stored", HttpStatus.CREATED);
        return responseEntity;
    }

    //GetAll tracks in the databases
    @ApiOperation(value = "Get All Tracks", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() throws TrackNotFoundExeption {
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<List<Track>>(trackService.returnAllTracks(),HttpStatus.ACCEPTED);
        return responseEntity;
    }

    //Update the track if already exists
    @ApiOperation(value = "Update the track", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("track")
    public ResponseEntity<?> updateTracks(@RequestParam("trackid") int trackid,@RequestParam("comments") String comments) throws TrackNotFoundExeption {
        ResponseEntity responseEntity;
        Track track34=trackService.updateTrack(trackid,comments);
        responseEntity= new ResponseEntity<String>("Succees-track updated",HttpStatus.OK);
        return responseEntity;
    }

    //Delete the track
    @ApiOperation(value = "Delete track", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("track")
    public ResponseEntity<?> deleteTrack(@RequestParam int trackid) throws TrackNotFoundExeption {
        List<Track> sometrack=trackService.deletTrack(trackid);
        ResponseEntity responseEntity= new ResponseEntity<String>("Success-Track deleted",HttpStatus.ACCEPTED);
        return responseEntity;
    }
}
