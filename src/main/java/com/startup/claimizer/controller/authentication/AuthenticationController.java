package com.startup.claimizer.controller.authentication;

import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.service.AuthServiceImpl;
import com.waleedreda.core.common.AppResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(("/auth"))
@RestController
@Getter
@Setter
public class AuthenticationController {
    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/login")
    public AppResponse<UserDto> loginUser(@RequestBody UserDto userDetails) {
        return getAuthService().loginUser(userDetails);
    }
}
