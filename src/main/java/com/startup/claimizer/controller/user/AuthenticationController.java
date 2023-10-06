package com.startup.claimizer.controller.user;

import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.service.AuthServiceImpl;
import com.waleedreda.core.common.AppResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RequestMapping("/user")
@RestController
public class AuthenticationController {
    @Autowired
    AuthServiceImpl authService;

    @GetMapping("/")
    public String findAllCustomers(){
        return "HELLO THAT IS TEST API";
    }

    @PostMapping("/login")
    public AppResponse<UserDto> loginUser(@RequestBody UserDto userDetails) {
        return getAuthService().loginUser(userDetails);
    }
}
