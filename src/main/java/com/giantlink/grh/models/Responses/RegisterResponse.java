package com.giantlink.grh.models.Responses;

import com.giantlink.grh.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
