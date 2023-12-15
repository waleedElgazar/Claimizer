package com.startup.claimizer.service.user;

import com.startup.claimizer.common.CommonService;
import com.startup.claimizer.common.UserCommonService;
import com.startup.claimizer.criteria.UserCriteria;
import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.entity.UserEntity;
import com.startup.claimizer.mapper.UserMapper;
import com.startup.claimizer.repo.UserRepo;
import com.startup.claimizer.specification.UserSpecificationBuilder;
import com.waleedreda.core.common.AppResponse;
import com.waleedreda.core.common.AppResponseUtil;
import com.waleedreda.core.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
    public AppResponse<UserDto> saveUser(UserDto userDetails) {
        try {
            userDetails.setPassword(CommonService.encrypt(userDetails.getPassword()));
            UserEntity userEntity = getUserMapper().ConvertToEntity(userDetails);
            UserEntity savedEntity = getUserRepo().save(userEntity);
            savedEntity.setPassword(userEntity.getPassword());
            return AppResponseUtil.buildSuccessResponse(getUserMapper().ConvertToDto(savedEntity));
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return AppResponseUtil.buildFailedResponse(ErrorCode.DUPLICATE_DATA, "User with the following email " + userDetails.getEmail() + " already exists ");
        } catch (Exception exception) {
            return AppResponseUtil.buildFailedResponse(ErrorCode.GENERAL, "General error due to " + exception.getMessage());
        }
    }


    @Override
    public AppResponse<UserDto> updateUser(UserDto userDetails) {
        UserCriteria userCriteria = UserCommonService.prepareUserCriteria(userDetails);

        return null;
    }
}
