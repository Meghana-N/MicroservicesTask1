package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**This is repository layer that
 * extends the JPA repository class with 2 parameters
 * the 1st is Entity class and second is wrapper type of the
 * id within the Entity class
 */

@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {
//
//    //This is making a new method other than ones existing in JPA repo
//    //The below query runs internally when the method given below is called
//    @Query(value = "SELECT t FROM Track t where t.trackname=?1")
//    public List<Track> fingBYTrackNAME(String name);

}
