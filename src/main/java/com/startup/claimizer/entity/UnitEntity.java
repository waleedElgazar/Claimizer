package com.startup.claimizer.entity;

import com.waleedreda.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UNIT_ENTITY")
@Entity
public class UnitEntity extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_UNIT")
    @SequenceGenerator(name = "S_UNIT", sequenceName = "S_UNIT", allocationSize = 1)
    private Long id;
    @Column(name = "UNIT_CODE", unique = true)
    private String unitCode;
    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
}

