package com.codewithfun.store;

import com.codewithfun.store.entities.Address;
import com.codewithfun.store.entities.Profile;
import com.codewithfun.store.entities.User;
import com.codewithfun.store.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var userRepository = context.getBean(UserRepository.class);

        //To create and save a new user
        /*var user = User.builder()
                        .name("user1")
                        .email("user@test.net")
                        .password("password!")
                        .build();
        userRepository.save(user);*/

        //To fetch a user by id
        //System.out.println(userRepository.findById(1L).get().getEmail());

        // To fetch all users
        userRepository.findAll().forEach(u -> System.out.println(u.getName()));

        // To delete a user by id
        userRepository.deleteById(1L);

    }

}
