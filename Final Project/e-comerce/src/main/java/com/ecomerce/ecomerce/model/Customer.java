package com.ecomerce.ecomerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String emailAddress;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;



}