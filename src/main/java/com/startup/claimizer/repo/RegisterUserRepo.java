package com.startup.claimizer.repo;

import com.startup.claimizer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterUserRepo extends JpaRepository<UserEntity, Long> {
}
