package org.example.acsrecomapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryAddressId;
    private String address;
    private String numberPhone;
    private String receiverName;
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "userId")
    private Users users;
}
