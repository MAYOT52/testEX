package com.testex.subservice.web.model.user;

import com.testex.subservice.shared.Default;

import java.util.UUID;

public record UserWeb(UUID userID, String email) {
    @Default
    public UserWeb {
    }
}

