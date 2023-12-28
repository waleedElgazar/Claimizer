package com.startup.claimizer.service.user;

import com.startup.claimizer.common.CommonService;
import com.startup.claimizer.criteria.UserCriteria;
import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.dto.UserSessionData;
import com.startup.claimizer.entity.UserEntity;
import com.startup.claimizer.exception.DatabaseException;
import com.startup.claimizer.exception.GeneralException;
import com.startup.claimizer.mapper.UserMapper;
import com.startup.claimizer.repo.UserRepo;
import com.startup.claimizer.specification.UserSpecificationBuilder;
import com.startup.claimizer.util.UserSessionDataBuilder;
import com.waleedreda.core.common.AppResponse;
import com.waleedreda.core.common.AppResponseUtil;
import com.waleedreda.core.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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
            UserEntity userEntity = getUserMapper().ConvertToEntity(userDetails);
            userEntity.setPassword(CommonService.encrypt(userDetails.getPassword()));
            UserEntity savedEntity = getUserRepo().save(userEntity);
            savedEntity.setPassword(userDetails.getPassword());
            return AppResponseUtil.buildSuccessResponse(getUserMapper().ConvertToDto(savedEntity));
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return AppResponseUtil.buildFailedResponse(ErrorCode.DUPLICATE_DATA,
                    new DatabaseException("This unit with the following data " + userDetails.toString() + " is already existed ").getMessage());
        } catch (Exception exception) {
            return AppResponseUtil.buildFailedResponse(ErrorCode.GENERAL, new GeneralException("General error due to " + exception.getMessage()).getMessage());
        }
    }


    @Override
    public AppResponse<UserDto> updateUser(UserDto userDto) {
        UserCriteria userCriteria = prepareUserCriteria();
        Specification<UserEntity> searchSpecification = getUserSpecificationBuilder().getSearchSpecification(userCriteria);
        UserEntity savedUser = getUserRepo().findAll(searchSpecification).get(0);
        if (null == savedUser) {
            return AppResponseUtil.buildFailedResponse(ErrorCode.NOT_FOUND, new DatabaseException("The user with this Criteria " + userCriteria.toString() + " not found").getMessage());
        } else {
            UserEntity updatedUserEntity = prepareUpdateUserEntity(savedUser, userDto);
            getUserRepo().save(updatedUserEntity);
            updatedUserEntity.setPassword(userDto.getPassword());
            return AppResponseUtil.buildSuccessResponse(getUserMapper().ConvertToDto(updatedUserEntity));
        }
    }

    private UserEntity prepareUpdateUserEntity(UserEntity userEntity, UserDto userDto) {
        if (null != userDto.getPassword()) {
            userEntity.setPassword(CommonService.encrypt(userDto.getPassword()));
        }
        if (null != userDto.getEmail()) {
            userEntity.setEmail(userDto.getEmail());
        }
        if (null != userDto.getName()) {
            userEntity.setName(userDto.getName());
        }
        if (null != userDto.getMobile()) {
            userEntity.setMobile(userDto.getMobile());
        }
        return userEntity;
    }

    private UserCriteria prepareUserCriteria() {
        UserSessionData userDataSession = UserSessionDataBuilder.getUserDataSession();
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setEmail(userDataSession.getEmail());
        userCriteria.setName(userDataSession.getName());
        userCriteria.setMobile(userDataSession.getMobile());
        return userCriteria;
    }
}
