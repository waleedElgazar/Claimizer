package com.startup.claimizer.service;

import com.startup.claimizer.common.CommonService;
import com.startup.claimizer.common.UserCommonService;
import com.startup.claimizer.criteria.UserCriteria;
import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.entity.UserEntity;
import com.startup.claimizer.mapper.UserMapper;
import com.startup.claimizer.repo.UserRepo;
import com.startup.claimizer.specification.UserSpecificationBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Getter
@Setter
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserSpecificationBuilder userSpecificationBuilder;

    @Override
    public UserDto saveUser(Map<String, String> userDetails) {
        UserDto userDto = UserCommonService.prepareUserDto(userDetails);
        UserEntity userEntity = getUserMapper().ConvertToEntity(userDto);
        UserEntity savedEntity = getUserRepo().save(userEntity);
        return getUserMapper().ConvertToDto(savedEntity);
    }

    @Override
    public UserDto loginUser(Map<String, String> userDetails) {
        UserCriteria userCriteria = UserCommonService.prepareUserCriteria(userDetails);
        Specification<UserEntity> searchSpecification = getUserSpecificationBuilder().getSearchSpecification(userCriteria);
        UserEntity userEntity = getUserRepo().findAll(searchSpecification).get(0);
        UserDto userDto = getUserMapper().ConvertToDto(userEntity);
        if (null != userDto) {
            String password = CommonService.encrypt(userDetails.get("password"));
            if (userDto.getPassword().equals(password)) {
                return userDto;
            }
        }
        return null;
    }
}
