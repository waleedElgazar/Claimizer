package com.startup.claimizer.dto;

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
}
