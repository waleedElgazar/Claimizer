package com.startup.claimizer.service;

import com.startup.claimizer.common.CommonService;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserSpecificationBuilder userSpecificationBuilder;

    @Override
    public AppResponse<UserDto> loginUser(UserDto userDetails) {
        try {
            UserCriteria userCriteria = new UserCriteria();
            userCriteria.setEmail(userDetails.getEmail());
            Specification<UserEntity> searchSpecification = getUserSpecificationBuilder().getSearchSpecification(userCriteria);
            List<UserEntity> userEntityList = getUserRepo().findAll(searchSpecification);
            if (userEntityList.isEmpty()) {
                return AppResponseUtil.buildFailedResponse(ErrorCode.NOT_FOUND, "This user with the Criteria " + userDetails.toString() + " isn't found");
            }
            UserDto userDto = getUserMapper().ConvertToDto(userEntityList.get(0));
            String password = CommonService.decrypt(userDetails.getPassword());
            if (userDto.getPassword().equals(password)) {
                userDto.setPassword(userDetails.getPassword());
                return AppResponseUtil.buildSuccessResponse(userDto);
            } else {
                return AppResponseUtil.buildFailedResponse(ErrorCode.INVALID_DATA, "Invalid Password");
            }
        } catch (Exception exception) {
            // TODO need to change the error code
            return AppResponseUtil.buildFailedResponse(ErrorCode.NOT_FOUND, "Error happen while during the request the error msg is " + exception.getMessage());
        }
    }

}
