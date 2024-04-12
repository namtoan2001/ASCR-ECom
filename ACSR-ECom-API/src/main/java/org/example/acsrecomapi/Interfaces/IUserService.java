package org.example.acsrecomapi.Interfaces;

import org.example.acsrecomapi.DTO.User.SignInRequest;
import org.example.acsrecomapi.DTO.User.SignUpRequest;
import org.example.acsrecomapi.DTO.User.UserToken;

import java.util.concurrent.CompletableFuture;

public interface IUserService {
    boolean signUpUser(SignUpRequest request) throws Exception;
    CompletableFuture<UserToken> signIn(SignInRequest request);
}
