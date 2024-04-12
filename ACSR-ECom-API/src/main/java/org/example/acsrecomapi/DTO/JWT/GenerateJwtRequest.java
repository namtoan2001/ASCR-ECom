package org.example.acsrecomapi.DTO.JWT;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateJwtRequest {
    private long userId;
    private String userName;
    private String fullName;
    private String email;
    private String numberPhone;
    private boolean isActive;

    public GenerateJwtRequest(long userId, String userName, String fullName, String numberPhone, String email, boolean active) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.isActive = isActive();
    }
}
