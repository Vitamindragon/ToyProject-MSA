package org.prj.user.web.service;

import org.prj.user.web.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<UserEntity> createMember(UserEntity user);

    Optional<UserEntity> getMemberByUserId(String userId);

    Iterable<UserEntity> getAllMembers();

    Optional<UserEntity> getMemberByEmail(String email);
}
