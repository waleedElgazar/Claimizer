package com.startup.claimizer.mapper;

import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.entity.UserEntity;
import com.waleedreda.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<UserDto, UserEntity> {

    @Override
    public Class<UserDto> getDtoClass() {
        return UserDto.class;
    }

    @Override
    public Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    @Override
    public UserDto ConvertToDto(UserEntity entity) {
        return super.ConvertToDto(entity);
    }

    @Override
    public UserEntity ConvertToEntity(UserDto dto) {
        return super.ConvertToEntity(dto);
    }
}
