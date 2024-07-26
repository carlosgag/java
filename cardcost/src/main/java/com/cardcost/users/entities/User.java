package com.cardcost.users.entities;

import com.cardcost.persistence.data.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@Validated
@Builder
public class User implements Serializable {
    private String user;
    private String password;
    private Role role;
}
