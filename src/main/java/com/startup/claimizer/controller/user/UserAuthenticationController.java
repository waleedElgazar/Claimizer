package com.startup.claimizer.controller.user;

import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.service.UserServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Getter
@Setter
@RequestMapping("/user")
@RestController
public class UserAuthenticationController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public UserDto loginUser(@RequestParam Map<String, String> userDetails){
        return getUserService().loginUser(userDetails);
    }
}
