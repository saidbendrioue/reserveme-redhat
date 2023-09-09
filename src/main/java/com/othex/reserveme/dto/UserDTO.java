package com.othex.reserveme.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.othex.reserveme.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    
    @JsonIgnore
    private String password;
}
