package org.example.acsrecomapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userName;
    private String fullName;
    private String passWord;
    private String email;
    private String numberPhone;
    private String address;
    private boolean isActive;
}
