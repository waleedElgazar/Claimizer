package com.startup.claimizer.entity;

import com.waleedreda.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "APPLICATION_CONFIGURATION_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationConfigurationEntity extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_APPLICATION_CONFIGURATION")
    @SequenceGenerator(name = "S_APPLICATION_CONFIGURATION", sequenceName = "S_APPLICATION_CONFIGURATION", allocationSize = 1)
    private Long id;

    @Column(name = "GROUP_NAME", nullable = false)
    private String groupName;


    @Column(name = "PARAMETER_KEY", unique = true, nullable = false)
    private String parameterKey;


    @Column(name = "PARAMETER_VALUE", nullable = false)
    private String parameterValue;
}
