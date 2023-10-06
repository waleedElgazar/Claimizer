package com.startup.claimizer.service;

import com.startup.claimizer.dto.UserDto;
import com.waleedreda.core.common.AppResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    public AppResponse<UserDto> saveUser (UserDto userDetails);
    public AppResponse<UserDto>updateUser (UserDto userDetails);

}
