package com.codewithfun.store.repositories;

import com.codewithfun.store.dtos.ProductSummary;
import com.codewithfun.store.dtos.ProductSummaryDTO;
import com.codewithfun.store.entities.Category;
import com.codewithfun.store.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
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


    // Call stored procedure from database
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying //Indicates that the query is an update or delete operation. must be used with @Query for update/delete queries
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    //Projection - selecting specific fields instead of entire entity

    // Using projection interface - way 1
    List<ProductSummary> findByCategory(Category category);

    // Using projection class - way 2
    // List<ProductSummaryDTO> findByCategory(Category category);


    // Using @Query with projection interface - way 3
    // @Query("select p.id, p.name from Product p where p.category = :category") //if we select entire p entity, we will encounter eager loading issue along with all fields. So, we use projection interface to select only required fields.
    // List<ProductSummary> findByCategory(@Param("category") Category category);

    // Using @Query with projection class - way 4
    // when using Class, we can't use select p.id, p.name directly. We have to use 'new' keyword along with fully qualified class name and constructor parameters.
    //@Query("select new com.codewithfun.store.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
    //List<ProductSummaryDTO> findByCategory(@Param("category") Category category);

}
