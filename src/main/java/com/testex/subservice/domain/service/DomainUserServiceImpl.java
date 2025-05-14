package com.testex.subservice.domain.service;

import com.testex.subservice.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class DomainUserServiceImpl implements DomainUserService {
    @Override
    public User createUser(String email) {
        return new User(email);
    }
}
