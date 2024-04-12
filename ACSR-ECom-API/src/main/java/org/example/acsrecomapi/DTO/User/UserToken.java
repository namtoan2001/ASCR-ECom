package org.example.acsrecomapi.DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToken {
    private String JwToken;

    public UserToken(String JwToken) {
        this.JwToken = JwToken;
    }
}
