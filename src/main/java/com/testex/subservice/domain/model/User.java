package com.testex.subservice.domain.model;

import com.testex.subservice.shared.Default;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID userID;
    private String email;

    public  User(){}


    public User(String email){
        UUID uuid = null;
        this.email = email;
    }

    @Default
    public User(UUID userID, String email) {
        this.userID = userID;
        this.email = email;
    }
}
