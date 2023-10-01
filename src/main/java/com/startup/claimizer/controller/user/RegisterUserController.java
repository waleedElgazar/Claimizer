package com.startup.claimizer.controller.user;

import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(("/user"))
@RestController
@Getter
@Setter
public class RegisterUserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestParam Map<String, String> userDetails){
        return getUserService().saveUser(userDetails);
    }
}
