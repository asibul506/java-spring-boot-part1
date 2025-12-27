package com.codewithfun.store.repositories;

import com.codewithfun.store.dtos.UserSummary;
import com.codewithfun.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {



    //@EntityGraph(attributePaths = {"user"})
    //@Query("SELECT p FROM Profile p JOIN User u ON p.id = u.id ORDER BY u.email")
    //List<Profile> findByLoyaltyPoints(Integer loyaltyPoints);

}

