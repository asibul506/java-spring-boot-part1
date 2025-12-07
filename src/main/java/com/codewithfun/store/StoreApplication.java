package com.codewithfun.store;

import com.codewithfun.store.entities.Address;
import com.codewithfun.store.entities.Profile;
import com.codewithfun.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {

        //ApplicationContext context = SpringApplication.run(StoreApplication.class, args);


        //This is another way to create an object of User class with Lombok Builder annotation. Here without passing id field the object is created
        var user = User.builder()
                        .name("Builder")
                        .email("Builder.test.net")
                        .password("password")
                        .build();


       var profile = Profile.builder().bio("Bio").build();
       user.setProfile(profile);
       profile.setUser(user);

       System.out.println(user);

    }

}
