package com.testex.subservice.web.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(@NotBlank @Email(message = "Email error") String email) {
}
