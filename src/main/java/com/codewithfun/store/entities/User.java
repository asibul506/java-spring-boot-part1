package com.codewithfun.store.entities;

import jakarta.persistence.*;
import lombok.*;

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

}
