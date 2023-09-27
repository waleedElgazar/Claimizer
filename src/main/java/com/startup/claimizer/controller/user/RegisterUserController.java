package com.startup.claimizer.controller.user;

import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.service.RegisterUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController("/")
@Getter
@Setter
public class RegisterUserController {
    @Autowired
    RegisterUserService registerUserService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestParam HashMap<String, Object> formData){

        UserDto userDto = new UserDto();
        userDto.setEmail((String) formData.get("email"));
        userDto.setPassword((String) formData.get("password"));
        userDto.setMobile((String) formData.get("mobile"));
        userDto.setName((String) formData.get("name"));
        return getRegisterUserService().saveUser(userDto);
//        return userDto;
    }
}
