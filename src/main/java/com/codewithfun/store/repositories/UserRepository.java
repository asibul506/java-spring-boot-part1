package com.codewithfun.store.repositories;

import com.codewithfun.store.dtos.UserSummary;
import com.codewithfun.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(attributePaths = {"addresses", "tags"}) // Eagerly fetch addresses and tags with user entity
    Optional<User> findByEmail(String email);

    // Fetch all users with their addresses using a custom query to deal with N+1 problem
    @EntityGraph(attributePaths = {"addresses"})
    @Query("SELECT u FROM User u")
    List<User> findAllWithAddresses();


    @Query("select u.id as id, u.email as email from User u where u.profile.loyaltyPoints > :loyaltyPoints order by u.email")
    List<UserSummary> findLoyalUsers(@Param("loyaltyPoints") Integer loyaltyPoints);

    // below this line, add various method signatures for Spring Data JPA query generation. when uncommented,
    // these methods will be automatically implemented by Spring Data JPA based on their names.
/* =========================
       BASIC CRUD / LOOKUPS
       ========================= */

    //User findByEmail(String email); // Finds user by exact email match
    //Optional<User> findByName(String username); // Safe lookup, may return empty
    //boolean existsByEmail(String email); // Checks if a user exists with given email
    //long countByActiveTrue(); // Counts only active users


    /* =========================
       STRING MATCHING
       ========================= */

    //List<User> findByNameLike(String name); // SQL LIKE (must include % manually)
    //List<User> findByNameContaining(String name); // name LIKE %value%
    //List<User> findByNameStartingWith(String prefix); // name LIKE value%
    //List<User> findByNameEndingWith(String suffix); // name LIKE %value
    //List<User> findByNameIgnoreCase(String name); // Case-insensitive equality
    //List<User> findByNameContainingIgnoreCase(String name); // Case-insensitive partial match


    /* =========================
       NUMERIC / COMPARABLE
       ========================= */

    /* List<User> findByAge(Integer age); // Exact age match
    List<User> findByAgeGreaterThan(Integer age); // age > value
    List<User> findByAgeGreaterThanEqual(Integer age); // age >= value
    List<User> findByAgeLessThan(Integer age); // age < value
    List<User> findByAgeBetween(Integer min, Integer max); // age BETWEEN min AND max
    List<User> findByBalanceGreaterThan(BigDecimal amount); // Numeric comparison (BigDecimal) */

    /* =========================
       BOOLEAN CONDITIONS
       ========================= */
/*
    List<User> findByActiveTrue(); // All active users
    List<User> findByActiveFalse(); // All inactive users
    List<User> findByVerifiedTrue(); // All verified users

*/
    /* =========================
       NULL CHECKS
       ========================= */

 //   List<User> findByPhoneNumberIsNull(); // Users without phone number
  //  List<User> findByAddressIsNotNull(); // Users having an address

    /* =========================
       DATE / TIME QUERIES
       ========================= */

    //List<User> findByCreatedAtAfter(LocalDateTime date); // Created after a date
    //List<User> findByCreatedAtBefore(LocalDateTime date); // Created before a date
  //  List<User> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end); // Between dates
    //List<User> findByDateOfBirthBefore(LocalDateTime date); // Born before a date

    /* =========================
       IN / NOT IN
       ========================= */

    //List<User> findByIdIn(List<Long> ids); // id IN (...)
    //List<User> findByEmailIn(Set<String> emails); // email IN (...)
    //List<User> findByIdNotIn(List<Long> ids); // id NOT IN (...)


    /* =========================
       LOGICAL OPERATORS
       ========================= */

    //List<User> findByNameAndActiveTrue(String name); // AND condition
    //List<User> findByNameOrEmail(String name, String email); // OR condition
    //List<User> findByAgeGreaterThanAndActiveTrue(Integer age); // Mixed condition


    /* =========================
       SORTING (OrderBy)
       ========================= */

    //List<User> findByActiveTrueOrderByNameAsc(); // Sort by name ASC
    //List<User> findByActiveTrueOrderByCreatedAtDesc(); // Sort by date DESC
    //List<User> findByNameOrderByAgeAscCreatedAtDesc(String name); // Multi-column sorting


    /* =========================
       LIMITING RESULTS
       ========================= */

    //List<User> findTop5ByActiveTrue(); // LIMIT 5
    //List<User> findFirst10ByNameContainingOrderByCreatedAtDesc(String name); // First 10 sorted


    /* =========================
       DISTINCT
       ========================= */

    //List<User> findDistinctByName(String name); // Removes duplicates
    //List<User> findDistinctByEmailContainingIgnoreCase(String email); // Unique emails


    /* =========================
       RELATIONSHIP / NESTED
       (Assumes User → Role relation)
       ========================= */

    //List<User> findByRoleName(String roleName); // JOIN role table
    //List<User> findByRoleId(Long roleId); // Lookup by FK


    /* =========================
       DELETE OPERATIONS
       ========================= */

    //void deleteByEmail(String email); // Deletes user by email
    //void deleteByCreatedAtBefore(LocalDateTime date); // Bulk delete old users


}