package com.codewithfun.store.repositories;

import com.codewithfun.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {

}
