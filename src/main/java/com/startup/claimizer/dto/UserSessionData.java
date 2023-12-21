package com.startup.claimizer.dto;

import com.startup.claimizer.enums.LanguageEnum;
import com.startup.claimizer.enums.ThemeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserSessionData {
    private String name;
    private String password;
    private String email;
    private String mobile;
    private String imageUrl;
    private LanguageEnum locale;
    private ThemeEnum theme;
}
