package com.codewithfun.store.entities;

import jakarta.persistence.*;

@Entity // This annotation specifies that the class is an entity and is mapped to a database table
@Table(name = "users") // This annotation specifies the name of the database table to be used for mapping
public class User {

    @Id // This annotation specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)// This annotation provides for the specification of generation strategies for the values of primary keys
    private Long id;

/*  This (Column) annotation is used to specify the mapped column for a persistent property or field. If no Column annotation is specified, the default values
    are applied. Additionally, we are specifying the column names in the database table. if the column name is the same as the field name, this annotation is
    optional. otherwise, it is necessary. because it helps to map the field to the correct column in the database table.
*/
    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
