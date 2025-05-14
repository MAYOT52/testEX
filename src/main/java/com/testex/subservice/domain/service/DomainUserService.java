package com.testex.subservice.domain.service;

import com.testex.subservice.domain.model.User;

public interface DomainUserService {
    User createUser(String email);
}
