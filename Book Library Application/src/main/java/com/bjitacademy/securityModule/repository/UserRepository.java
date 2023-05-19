package com.bjitacademy.securityModule.repository;

import com.bjitacademy.securityModule.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String email);
}
