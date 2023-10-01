package com.startup.claimizer.common;

import com.startup.claimizer.criteria.UserCriteria;
import com.startup.claimizer.dto.UserDto;

import java.util.Map;

public abstract class UserCommonService {
    private UserCommonService() {
    }


    public static UserDto prepareUserDto (Map<String,String> userDetails){
        UserDto userDto = new UserDto();
        userDto.setPassword(CommonService.encrypt(userDetails.get("password")));
        userDto.setEmail(userDetails.get("email"));
        userDto.setName(userDetails.get("name"));
        userDto.setMobile(userDetails.get("mobile"));
        return userDto;
    }

    public static UserCriteria prepareUserCriteria (Map<String,String> userDetails){
        UserCriteria userCriteria = new UserCriteria();
//        userCriteria.setPassword(CommonService.encrypt(userDetails.get("password")));
        userCriteria.setEmail(userDetails.get("email"));
        userCriteria.setName(userDetails.get("name"));
        userCriteria.setMobile(userDetails.get("mobile"));
        return userCriteria;
    }
}
