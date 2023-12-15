package com.startup.claimizer.controller.unit;

import com.startup.claimizer.dto.UnitDto;
import com.startup.claimizer.service.unit.UnitService;
import com.waleedreda.core.common.AppResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Getter
@Setter
@RequestMapping("/unit")
@RestController
public class UnitController {

    @Autowired
    UnitService unitService;

    @PostMapping("/joinUnit")
    private AppResponse<UnitDto> joinUnit(@RequestBody UnitDto unitDto) {
        Date date = new Date();
        return getUnitService().joinUnit(unitDto);
    }
}
