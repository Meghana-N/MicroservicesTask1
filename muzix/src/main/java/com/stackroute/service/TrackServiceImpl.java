package com.stackroute.service;

import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundExeption;
import com.stackroute.repository.TrackRepository;
import com.stackroute.domain.Track;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**This class implements the track service interfacea
 * and implements the methods, this class uses the inbuilt methods of
 * JPA repository to perform CRUD operations
 */

@Service
public class TrackServiceImpl implements TrackService {

    //Making variable of trackrepository type
    private TrackRepository trackRepository;

    //This is setting the trackrepository variable by constructor injection
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //This method saves the Track Object passed as parameter in the database
    @Override
    public Track saveTheTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getTrackid())) {
            throw new TrackAlreadyExistsException("track already exists");
        }
        trackRepository.save(track);
        return track;
    }

    //This method returns all the tracks present in the Database
    @Override
    public List<Track> returnAllTracks() throws TrackNotFoundExeption {
        List<Track> tracklist = trackRepository.findAll();
        if (tracklist.isEmpty() || tracklist == null) {
            throw new TrackNotFoundExeption("Error while returning the tracks");
        }
        return tracklist;
    }

    //This method updates an existing track with the new Track object passed by matching id
    @Override
    public Track updateTrack(int trackId,String comments) throws TrackNotFoundExeption {
        if(!trackRepository.existsById(trackId)){
            throw new TrackNotFoundExeption("error in updating track");
        }
        Optional<Track> track=trackRepository.findById(trackId);
        Track track1=track.get();
        track1.setComments(comments);
        trackRepository.save(track1);
        return track1;
    }

    //This method is used to delete a track from database by mapping the entry using
    // track id passed as parameter
    @Override
    public List<Track> deletTrack(int trackid) throws TrackNotFoundExeption {
        if (trackRepository.existsById(trackid)==false) {
            throw new TrackNotFoundExeption("Error in deleting track");
        }
        trackRepository.deleteById(trackid);
        return trackRepository.findAll();
    }

    //This is used to get track from the database by using the name passed as parameter
    //and mapping it to the trackname in database
//    @Override
//    public List<Track> getByTrackNAme(String name) throws TrackNotFoundExeption {
//        List<Track> tracks= trackRepository.fingBYTrackNAME(name);
//        if (tracks.isEmpty()||tracks==null) {
//            throw new TrackNotFoundExeption("Error while getting track by name");
//        }
//        return tracks;
//    }
}
