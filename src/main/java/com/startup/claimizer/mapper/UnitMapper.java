package com.startup.claimizer.mapper;

import com.startup.claimizer.dto.UnitDto;
import com.startup.claimizer.entity.UnitEntity;
import com.waleedreda.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper extends BaseMapper<UnitDto, UnitEntity> {
    @Override
    public Class<UnitDto> getDtoClass() {
        return UnitDto.class;
    }

    @Override
    public Class<UnitEntity> getEntityClass() {
        return UnitEntity.class;
    }
}
