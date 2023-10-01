package com.startup.claimizer.service;

import com.startup.claimizer.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    public UserDto saveUser (Map<String,String> userDetails);
    public UserDto loginUser (Map<String,String> userDetails);
}
