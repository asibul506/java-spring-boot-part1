package com.codewithfun.store.repositories;

import com.codewithfun.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
