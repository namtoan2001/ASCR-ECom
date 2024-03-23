package org.example.acsrecomapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productImageId;
    private String imageLink;
    @ManyToOne
    @JoinColumn(name = "products", referencedColumnName = "productId")
    private Products products;
}
