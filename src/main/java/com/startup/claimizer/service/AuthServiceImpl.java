package com.startup.claimizer.service;

import com.startup.claimizer.common.CommonService;
import com.startup.claimizer.criteria.UserCriteria;
import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.entity.UserEntity;
import com.startup.claimizer.mapper.UserMapper;
import com.startup.claimizer.repo.UserRepo;
import com.startup.claimizer.specification.UserSpecificationBuilder;
import com.startup.claimizer.util.JwtUtil;
import com.waleedreda.core.common.AppResponse;
import com.waleedreda.core.common.AppResponseUtil;
import com.waleedreda.core.common.ErrorCode;
import com.waleedreda.core.common.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    @Autowired
    JwtUtil jwtUtil;

    Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    @Override
    public AppResponse<UserDto> loginUser(UserDto userDetails) {
        logger.info("Login Request the request body is ", userDetails.toString());
        try {
            UserCriteria userCriteria = new UserCriteria();
            userCriteria.setEmail(userDetails.getEmail());
            Specification<UserEntity> searchSpecification = getUserSpecificationBuilder().getSearchSpecification(userCriteria);
            List<UserEntity> userEntityList = getUserRepo().findAll(searchSpecification);
            if (userEntityList.isEmpty()) {
                logger.error("Login Request the Response body is This user with the Criteria " + userDetails.toString() + " isn't found");
                return AppResponseUtil.buildFailedResponse(ErrorCode.NOT_FOUND, "This user with the Criteria " + userDetails.toString() + " isn't found");
            }
            UserDto userDto = getUserMapper().ConvertToDto(userEntityList.get(0));
            String password = CommonService.encrypt(userDetails.getPassword());
            if (userDto.getPassword().equals(password)) {
                userDto.setPassword(userDetails.getPassword());
                logger.info("Login Request the Response body is " + userDto.toString());
                String token = getJwtUtil().createToken(userDto);
                userDto.setToken(token);
                return AppResponseUtil.buildSuccessResponse(userDto);
            } else {
                logger.error("Login Response the Response body is " + ErrorCode.INVALID_DATA.toString());
                return AppResponseUtil.buildFailedResponse(ErrorCode.INVALID_DATA, "Invalid Password");
            }
        } catch (Exception exception) {
            logger.error("Login Response the Response body is " + ErrorCode.GENERAL.toString());
            return AppResponseUtil.buildFailedResponse(ErrorCode.GENERAL, "Error happen while during the request the error msg is " + exception.getMessage());
        }
    }

}
