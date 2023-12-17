package com.startup.claimizer.dto;

import com.startup.claimizer.enums.LanguageEnum;
import com.startup.claimizer.enums.ThemeEnum;
import com.waleedreda.core.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String mobile;
    private String imageUrl;
    private LanguageEnum locale;
    private ThemeEnum theme;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", locale=" + locale +
                ", theme=" + theme +
                '}';
    }
}
