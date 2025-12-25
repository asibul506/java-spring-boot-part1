package com.codewithfun.store.repositories;

import com.codewithfun.store.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    //find products whose prices are in a given range and sort by name
    //SQL or JPQL query can be used

    //SQL query
    @Query(value = "select * from products where price between :min and :max order by name", nativeQuery = true) // nativeQuery = true indicates that the query is a native SQL query otherwise it is JPQL
    List<Product> findByPriceBetween(@Param("min") BigDecimal min, @Param("max") BigDecimal max);


    // JPQL query - Java Persistence Query Language , an object-oriented query language used to query entities
    @Query("select p from Product p join p.category where p.price between :min and :max order by p.name")
    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying //Indicates that the query is an update or delete operation. must be used with @Query for update/delete queries
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);




}
