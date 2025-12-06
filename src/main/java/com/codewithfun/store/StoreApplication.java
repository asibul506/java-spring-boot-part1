package com.codewithfun.store;

import com.codewithfun.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {

        //ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        //This is one way to create an object of User class with Lombok AllArgsConstructor annotation, Getter annotation and Setter annotation
        var user1 = new User(1L, "John Doe", "john@doe.com", "password123");

        System.out.println(user1.getEmail());
        System.out.println(user1.getName());
        System.out.println(user1.getPassword());

        user1.setPassword("password!");

        System.out.println(user1.getPassword());
        System.out.println(user1.getId());

        //This is another way to create an object of User class with Lombok Builder annotation. Here without passing id field the object is created
        var user2 = User.builder()
                        .name("Builder")
                        .email("Builder.test.net")
                        .password("password")
                        .build();

        System.out.println(user1);
        System.out.println(user2);


    }

}
