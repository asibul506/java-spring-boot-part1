package com.codewithfun.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter // This annotation is from Lombok library and it generates setter methods for all fields in the class
@Getter // This annotation is from Lombok library and it generates getter methods for all fields in the class
@AllArgsConstructor // This annotation is from Lombok library and it generates a constructor with parameters for all fields in the class. to use this annotation we need a NoArgsConstructor annotation as well or a default constructor
@NoArgsConstructor // This annotation is from Lombok library and it generates a no-argument constructor
@Builder // This annotation is from Lombok library and it helps to create object when some of the fields are optional unlike regular constructor where you have to pass all the fields
@ToString // This annotation is from Lombok library and it generates a toString() method that includes all fields in the class
@Entity // This annotation specifies that the class is an entity and is mapped to a database table
@Table(name = "users") // This annotation specifies the name of the database table to be used for mapping
public class User {

    @Id // This annotation specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)// This annotation provides for the specification of generation strategies for the values of primary keys
    @Column(name = "id") // This annotation specifies the mapped column for a persistent property or field
    private Long id;

/*
    This (Column) annotation is used to specify the mapped column for a persistent property or field. If no Column annotation is specified, the default values
    are applied. Additionally, we are specifying the column names in the database table. if the column name is the same as the field name, this annotation is
    optional. otherwise, it is necessary. because it helps to map the field to the correct column in the database table.
*/
    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true) // cascade type PERSIST is used to propagate the persist operation to the associated entities
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();


    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    @ManyToMany
    @JoinTable(
            name = "user_tags", // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key column referencing User entity
            inverseJoinColumns = @JoinColumn(name = "tag_id") // Foreign key column referencing Tag entity
    ) // Join table to establish many-to-many relationship between User and Tag
    @Builder.Default
    private Set<Tag> tags = new HashSet<>(); //Set to remove duplicate tags

    // Method to add a tag to the user
    public void addTag(String tagName) {
        var tag = new Tag(tagName);
        tags.add(tag);
        tag.getUsers().add(this);
    }

    //despite of setting the fetch strategy to LAZY on the owner side of the relationship, it is still EAGER by default
    // for OneToOne relationship. Among two options to chang this behavior, we can either comment out or remove this relationship
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE) // mappedBy attribute indicates that the Profile entity owns the relationship
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name="wishlist",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<Product> wishlist = new HashSet<>();


    public void addFavoriteProduct(Product product) {
        wishlist.add(product);
    }
}
