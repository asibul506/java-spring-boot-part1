package com.codewithfun.store;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}