package org.prj.user.web.service;

import org.prj.user.web.dto.UserDTO;
import org.prj.user.web.entity.User;
import java.util.Optional;

public interface UserService {

    Optional<User> createMember(User user);

    Optional<User> findByUserId(String userId);

    Iterable<User> findAllMembers();
}
