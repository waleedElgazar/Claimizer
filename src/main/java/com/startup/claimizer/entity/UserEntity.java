package com.startup.claimizer.entity;

import com.waleedreda.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ENTITY")
@Entity
public class UserEntity extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USER")
    @SequenceGenerator(name = "S_USER", sequenceName = "S_USER", allocationSize = 1)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "MOBILE", unique = true)
    private String mobile;
}
