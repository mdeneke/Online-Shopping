package com.ecomerce.ecomerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    private int quantity;

    private ItemStatus status;

    private String image;

    @ManyToOne()
//    @JsonIgnore
    @JoinColumn(name = "product_id") //column name
    private Product product;

}

 enum ItemStatus {
    IN_STOCK,
    OUT_OF_STOCK
}
