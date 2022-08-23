package com.giantlink.grh.controllers;


import com.giantlink.grh.models.Requests.LoginRequest;
import com.giantlink.grh.models.Requests.RegisterRequest;
import com.giantlink.grh.models.Responses.LoginResponse;
import com.giantlink.grh.models.Responses.RegisterResponse;
import com.giantlink.grh.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.giantlink.grh.repositories.RoleRepository;
import com.giantlink.grh.repositories.UserRepository;
import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthService authService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return new ResponseEntity<LoginResponse>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return new ResponseEntity<RegisterResponse>(authService.register(registerRequest), HttpStatus.CREATED);
    }

}
