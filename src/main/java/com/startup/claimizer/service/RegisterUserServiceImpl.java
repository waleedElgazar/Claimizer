package com.startup.claimizer.service;

import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.entity.UserEntity;
import com.startup.claimizer.mapper.RegisterUserMapper;
import com.startup.claimizer.repo.RegisterUserRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class RegisterUserServiceImpl implements RegisterUserService{
    @Autowired
    RegisterUserMapper registerUserMapper;
    @Autowired
    RegisterUserRepo registerUserRepo;

    @Override
    public UserDto saveUser(UserDto userDto) {
        UserEntity userEntity = getRegisterUserMapper().ConvertToEntity(userDto);
        UserEntity savedEntity = getRegisterUserRepo().save(userEntity);
        UserDto userDto1 = getRegisterUserMapper().ConvertToDto(savedEntity);
        return userDto1;
    }
}
