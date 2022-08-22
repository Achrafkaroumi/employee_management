package com.giantlink.grh.services.impl;

import com.giantlink.grh.config.jwt.JwtUtils;
import com.giantlink.grh.entities.ERole;
import com.giantlink.grh.entities.Role;
import com.giantlink.grh.entities.User;
import com.giantlink.grh.models.Requests.LoginRequest;
import com.giantlink.grh.models.Requests.RegisterRequest;
import com.giantlink.grh.models.Responses.LoginResponse;
import com.giantlink.grh.models.Responses.RegisterResponse;
import com.giantlink.grh.repositories.RoleRepository;
import com.giantlink.grh.repositories.UserRepository;
import com.giantlink.grh.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String generateToken = jwtUtils.generateToken(authenticate);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailsImpl principal = (UserDetailsImpl) authenticate.getPrincipal();
        List<String> collect = principal.getAuthorities().stream().map(role -> role.getAuthority())
                .collect(Collectors.toList());
        LoginResponse build = LoginResponse.builder().username(principal.getUsername()).roles(collect)
                .token(generateToken).build();
        return build;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
        registerRequest.setRoles(roles);
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User user = User.builder().username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .roles(roles).build();
        userRepository.save(user);

        return RegisterResponse.builder().username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .roles(registerRequest.getRoles())
                .email(registerRequest.getEmail())
                .id(user.getId())
                .build();
    }
}
