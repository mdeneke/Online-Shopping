package com.ecomerce.ecomerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @Transient
    private int itemId;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Transient
    private int customerId;
}