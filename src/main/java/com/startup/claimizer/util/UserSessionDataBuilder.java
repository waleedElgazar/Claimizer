package com.startup.claimizer.util;

import com.startup.claimizer.dto.UserSessionData;

public class UserSessionDataBuilder {
    private static UserSessionData userData;

    public static void setUserData(UserSessionData userSessionData) {
        userData = userSessionData;
    }

    public static UserSessionData getUserDataSession() {
        if (userData == null){
            return new UserSessionData();
        }else{
            return userData;
        }
    }
}
