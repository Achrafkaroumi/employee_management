package com.giantlink.grh.models.Responses;

import com.giantlink.grh.entities.ERole;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private String username;
    private List<String> roles;
}
