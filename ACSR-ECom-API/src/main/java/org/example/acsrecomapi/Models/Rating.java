package org.example.acsrecomapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long RatingId;
    private float totalScore;
    @ManyToOne
    @JoinColumn(name = "products", referencedColumnName = "productId")
    private Products products;
}
