package org.prj.user.repository;

import org.prj.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);
    UserEntity findByUserName(String username);

}
