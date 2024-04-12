package org.example.acsrecomapi.Controllers;

import org.example.acsrecomapi.Common.Constant;
import org.example.acsrecomapi.DTO.User.SignInRequest;
import org.example.acsrecomapi.DTO.User.SignUpRequest;
import org.example.acsrecomapi.DTO.User.UserToken;
import org.example.acsrecomapi.Services.UserService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = Constant.HOST, allowCredentials = "true")
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(path = "/signUp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean signUp(@ModelAttribute("request")@Validated SignUpRequest request) throws Exception {
        return userService.signUpUser(request);
    }
    @PostMapping(path = "/signIn", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<UserToken> signIn(@ModelAttribute("request")@Validated SignInRequest request) throws Exception {
        return userService.signIn(request);
    }
}
