package org.example.acsrecomapi.DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    private String userName;
    private String password;
}
