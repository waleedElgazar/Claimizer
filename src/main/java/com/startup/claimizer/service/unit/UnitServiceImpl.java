package com.startup.claimizer.service.unit;

import com.startup.claimizer.dto.UnitDto;
import com.startup.claimizer.entity.UnitEntity;
import com.startup.claimizer.exception.DatabaseException;
import com.startup.claimizer.exception.GeneralException;
import com.startup.claimizer.mapper.UnitMapper;
import com.startup.claimizer.repo.UnitRepo;
import com.waleedreda.core.common.AppResponse;
import com.waleedreda.core.common.AppResponseUtil;
import com.waleedreda.core.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class UnitServiceImpl implements UnitService {
    @Autowired
    UnitRepo unitRepo;
    @Autowired
    UnitMapper unitMapper;

    @Override
    public AppResponse<UnitDto> joinUnit(UnitDto unitDto) {
        try {
            UnitEntity unitEntity = getUnitMapper().ConvertToEntity(unitDto);
            UnitEntity savedEntity = getUnitRepo().save(unitEntity);
            return AppResponseUtil.buildSuccessResponse(getUnitMapper().ConvertToDto(savedEntity));
        } catch (ConstraintViolationException ex) {
            return AppResponseUtil.buildFailedResponse(ErrorCode.DUPLICATE_DATA,
                    new DatabaseException("This unit with the following data " + unitDto.toString() + " is already existed ").getMessage());
        }catch (Exception ex){
            return AppResponseUtil.buildFailedResponse(ErrorCode.GENERAL,
                    new GeneralException("General error happened " + ex.getMessage()).getMessage());
        }
    }
}



