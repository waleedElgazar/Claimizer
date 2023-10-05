package com.startup.claimizer.service;

import com.startup.claimizer.dto.UserDto;
import com.waleedreda.core.common.AppResponse;

public interface AuthService {
    public AppResponse<UserDto> loginUser (UserDto userDetails);
}
