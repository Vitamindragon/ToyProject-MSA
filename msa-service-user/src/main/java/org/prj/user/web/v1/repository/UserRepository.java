package org.prj.user.web.v1.repository;

import org.prj.user.web.v1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String username);
}
