package com.giantlink.grh.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.giantlink.grh.models.Requests.LoginRequest;
import com.giantlink.grh.models.Responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giantlink.grh.config.jwt.JwtUtils;
import com.giantlink.grh.entities.ERole;
import com.giantlink.grh.entities.Role;
import com.giantlink.grh.entities.User;

import com.giantlink.grh.repositories.RoleRepository;
import com.giantlink.grh.repositories.UserRepository;
import com.giantlink.grh.services.impl.UserDetailsImpl;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String generateToken = jwtUtils.generateToken(authenticate);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailsImpl principal = (UserDetailsImpl) authenticate.getPrincipal();
        List<String> collect = principal.getAuthorities().stream().map(role -> role.getAuthority())
                .collect(Collectors.toList());
        LoginResponse build = LoginResponse.builder().username(principal.getUsername()).roles(collect)
                .token(generateToken).build();

        return new ResponseEntity<LoginResponse>(build, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

}
