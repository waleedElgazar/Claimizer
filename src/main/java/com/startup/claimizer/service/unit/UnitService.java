package com.startup.claimizer.service.unit;

import com.startup.claimizer.dto.UnitDto;
import com.waleedreda.core.common.AppResponse;
import org.springframework.stereotype.Service;

public interface UnitService  {
    public AppResponse<UnitDto> joinUnit(UnitDto unitDto);
}
