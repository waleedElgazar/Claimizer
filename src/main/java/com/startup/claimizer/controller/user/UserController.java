package com.startup.claimizer.controller.user;

import com.startup.claimizer.criteria.UserCriteria;
import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.dto.UserSessionData;
import com.startup.claimizer.service.AuthServiceImpl;
import com.startup.claimizer.service.user.UserService;
import com.startup.claimizer.util.JwtUtil;
import com.waleedreda.core.common.AppResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(("/user"))
@RestController
@Getter
@Setter
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public AppResponse<UserDto> registerUser(@RequestBody UserDto userDetails) {
        return getUserService().saveUser(userDetails);
    }

    @PostMapping("/update")
    public AppResponse<UserDto> updateUser(@RequestBody UserCriteria userCriteria) {
//        UserSessionData.
        return getUserService().updateUser(userCriteria);
    }
}
