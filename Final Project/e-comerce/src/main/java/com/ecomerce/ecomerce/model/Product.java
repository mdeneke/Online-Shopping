package com.ecomerce.ecomerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//  @Column(name="product_name",length = 45, nullable = false)
    private String name;
//    @Lob
//    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @Transient
    public Resource imagePath;

    @Enumerated(EnumType.STRING) //good incase the order of the enums changes
    ProductStatus productStatus;

    //fetch = FetchType.LAZY
//    @OneToMany(mappedBy = "product",cascade = { CascadeType.ALL })
//    @JoinColumn(name = "item_id")
//    private List<Item> items = new ArrayList<Item>();

}