package com.testex.subservice.DTO;

import com.testex.subservice.shared.Default;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDTO {
    private final UUID userID;
    private final String email;


    @Default
    public UserDTO(UUID userID, String email) {
        this.userID = userID;
        this.email = email;
    }
}
