package com.startup.claimizer.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.waleedreda.core.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitDto extends BaseDto {
    private Long id;
    private String unitCode;
    private String contractNumber;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date endDate;

    @Override
    public String
    toString() {
        return "UnitDto{" +
                "id=" + id +
                ", unitCode='" + unitCode + '\'' +
                ", contractNumber='" + contractNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
