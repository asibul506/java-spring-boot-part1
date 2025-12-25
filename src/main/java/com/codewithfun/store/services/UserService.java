package com.codewithfun.store.services;

import com.codewithfun.store.entities.Address;
import com.codewithfun.store.entities.Category;
import com.codewithfun.store.entities.Product;
import com.codewithfun.store.entities.User;
import com.codewithfun.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager; // JPA's EntityManager is used to interact with the persistence context.

    @Transactional
    // This annotation is used to specify that the method should be executed within a transaction context.
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
        System.out.println("User: " + user.getId() + "\t" + user.getName());

        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println("Profile: " + profile.getId() + "\t" + profile.getBio());
        System.out.println("Associated User from Profile: " + profile.getUser().getId() + "\t" + profile.getUser().getName());

    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println("Address: " + address);
    }

    public void persistRelated() {
        var user = User.builder()
                .name("jon")
                .email("jon@doe.com")
                .password("jonpassword")
                .build();

        var address = Address.builder()
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .zip("62701")
                .build();

        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);

    }


    //Exercise 8.9 part 1 and 2
    @Transactional
    public void manageProducts() {

        /*var category = Category.builder()
                .name("Mobile")
                .build();
        categoryRepository.save(category);*/

        var category = categoryRepository.findById((byte) 1).orElseThrow();

        var product = Product.builder()
                .name("iphone 15 pro max")
                .price(BigDecimal.valueOf(1449.999))
                .category(category)
                .build();

        productRepository.save(product);
    }

    //Exercise 8.9 part 3
    @Transactional
    public void addProductToWishlist() {
        var user = userRepository.findById(1L).orElseThrow();
        var products = productRepository.findAll();

        products.forEach(user::addFavoriteProduct);
        userRepository.save(user);

    }

    //Exercise 8.9 part 4
    public void removeProduct() {
        productRepository.deleteById(3L);
    }

}
