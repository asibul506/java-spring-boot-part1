package com.codewithfun.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;


    @OneToOne
    @JoinColumn(name = "id") // foreign key column that references the primary key of the User entity
    @MapsId // This annotation is used to map the primary key of the Profile entity to the primary key of the User entity
    @ToString.Exclude
    private User user;

}
