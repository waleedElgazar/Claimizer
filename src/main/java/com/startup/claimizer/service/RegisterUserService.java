package com.startup.claimizer.service;

import com.startup.claimizer.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface RegisterUserService {
    public UserDto saveUser (UserDto userDto);
}
