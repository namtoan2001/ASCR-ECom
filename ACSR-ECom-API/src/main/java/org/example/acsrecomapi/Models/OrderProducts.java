package org.example.acsrecomapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderProductId;
    private int quantity;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "products", referencedColumnName = "productId")
    private Products products;
    @ManyToOne
    @JoinColumn(name = "orders", referencedColumnName = "orderId")
    private Orders orders;
}
