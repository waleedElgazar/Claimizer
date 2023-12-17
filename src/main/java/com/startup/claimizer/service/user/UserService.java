package com.startup.claimizer.service.user;

import com.startup.claimizer.criteria.UserCriteria;
import com.startup.claimizer.dto.UserDto;
import com.waleedreda.core.common.AppResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public AppResponse<UserDto> saveUser(UserDto userDetails);

    public AppResponse<UserDto> updateUser(UserCriteria userCriteria);

}
