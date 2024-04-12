package org.example.acsrecomapi.DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String userName;
    private String fullName;
    private String passWord;
    private String confirmPassword;
    private String email;
    private String numberPhone;
}
