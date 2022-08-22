package com.giantlink.grh.services;

import com.giantlink.grh.models.Requests.LoginRequest;
import com.giantlink.grh.models.Requests.RegisterRequest;
import com.giantlink.grh.models.Responses.LoginResponse;
import com.giantlink.grh.models.Responses.RegisterResponse;

public interface AuthService {
    LoginResponse login (LoginRequest loginRequest);
    RegisterResponse register (RegisterRequest registerRequest);
}
