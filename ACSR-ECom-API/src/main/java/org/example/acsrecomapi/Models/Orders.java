package org.example.acsrecomapi.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private int totalPrice;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    private String address;
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "userId")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "payments", referencedColumnName = "paymentId")
    private Payments payments;
}
