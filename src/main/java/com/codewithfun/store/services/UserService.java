package com.codewithfun.store.services;

import com.codewithfun.store.entities.User;
import com.codewithfun.store.repositories.AddressRepository;
import com.codewithfun.store.repositories.ProfileRepository;
import com.codewithfun.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager; // JPA's EntityManager is used to interact with the persistence context.

    @Transactional // This annotation is used to specify that the method should be executed within a transaction context.
    public void showEntityStates() {
        var user = User.builder()
                .name("user1")
                .email("user@test.net")
                .password("password!")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("The user entity is Persistent state.");
        } else {
            System.out.println("The user entity is in a TRANSIENT/ Detached state.");
        }

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("The user entity is Persistent state.");
        } else {
            System.out.println("The user entity is in a TRANSIENT/ Detached state.");
        }

    }

    @Transactional
    public void showRelatedEntities() {
        var user = userRepository.findById(2L).orElseThrow();
        System.out.println("User: " + user.getId() +"\t"+ user.getName());

        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println("Profile: " + profile.getId() +"\t"+ profile.getBio());
        System.out.println("Associated User from Profile: " + profile.getUser().getId() +"\t"+ profile.getUser().getName());

    }

    public void showAddresses() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println("Address: " + address);
    }
}
