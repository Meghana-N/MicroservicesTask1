package com.stackroute.repository;

import com.stackroute.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**This is repository layer that
 * extends the JPA repository class with 2 parameters
 * the 1st is Entity class and second is wrapper type of the
 * id within the Entity class
 */

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
}
