package org.example.acsrecomapi.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.example.acsrecomapi.Common.ExceptionOutput;
import org.example.acsrecomapi.DTO.JWT.GenerateJwtRequest;
import org.example.acsrecomapi.DTO.User.SignInRequest;
import org.example.acsrecomapi.DTO.User.SignUpRequest;
import org.example.acsrecomapi.DTO.User.UserToken;
import org.example.acsrecomapi.Interfaces.IJwtService;
import org.example.acsrecomapi.Interfaces.IUserService;
import org.example.acsrecomapi.Models.Users;
import org.example.acsrecomapi.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IJwtService jwtService;
    private final HttpServletRequest httpServletRequest;
    public UserService(UserRepository userRepository, IJwtService jwtService, HttpServletRequest httpServletRequest) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.httpServletRequest = httpServletRequest;
    }
    public Claims getJwtClaims() throws JwtException {
        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length()).trim();
            return jwtService.decodeJwt(token);
        }
        return null;
    }
    @Override
    public boolean signUpUser(SignUpRequest request) throws Exception {
        Users checkInput = userRepository.findByUserNameAndNumberPhoneAndEmail(request.getUserName(), request.getNumberPhone(), request.getEmail());
        if(checkInput != null){
            throw new Exception(ExceptionOutput.INVALID);
        }
        if(!request.getPassWord().equals(request.getConfirmPassword())){
            throw new Exception(ExceptionOutput.WRONG_CONFIRM_PASSWORD);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode(request.getPassWord());
        Users signUp = new Users();
        signUp.setUserName(request.getUserName());
        signUp.setFullName(request.getFullName());
        signUp.setPassWord(passwordHash);
        signUp.setEmail(request.getEmail());
        signUp.setNumberPhone(request.getNumberPhone());
        signUp.setActive(true);
        userRepository.save(signUp);
        return true;
    }

    @Override
    public CompletableFuture<UserToken> signIn(SignInRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            Users user = userRepository.findByUserName(request.getUserName());
            if (user == null) {
                throw new RuntimeException(ExceptionOutput.NOT_FOUND);
            }
            boolean isCorrect = new BCryptPasswordEncoder().matches(request.getPassword(), user.getPassWord());
            if (!isCorrect) {
                throw new RuntimeException(ExceptionOutput.WRONG_PASSWORD);
            }
            GenerateJwtRequest jwtRequest = new GenerateJwtRequest(
                    user.getUserId(),
                    user.getUserName(),
                    user.getFullName(),
                    user.getNumberPhone(),
                    user.getEmail(),
                    user.isActive()
            );
            String token = jwtService.generateJwt(jwtRequest);
            return new UserToken(token);
        });
    }
}