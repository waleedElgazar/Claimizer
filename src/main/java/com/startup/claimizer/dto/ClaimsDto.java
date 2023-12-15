package com.startup.claimizer.dto;

import com.waleedreda.core.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsDto extends BaseDto {
    private Long id;
    private Long unitId;
    private Long categoryId;
    private Long subCategoryId;
    private Long claimTypeId;
    private String description;
    //todo concatenate the both
    private Date availableDate;
    private Date availableTime;
    private File file;

    @Override
    public String toString() {
        return "ClaimsDto{" +
                "id=" + id +
                ", unitId=" + unitId +
                ", categoryId=" + categoryId +
                ", subCategoryId=" + subCategoryId +
                ", claimTypeId=" + claimTypeId +
                ", description='" + description + '\'' +
                ", availableDate=" + availableDate +
                ", availableTime=" + availableTime +
                ", file=" + file +
                '}';
    }
}
