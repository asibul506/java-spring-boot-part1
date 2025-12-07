package com.codewithfun.store.repositories;

import com.codewithfun.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
