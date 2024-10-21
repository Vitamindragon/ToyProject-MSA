package org.prj.user.web.v1.service;

import org.prj.user.web.v1.dto.UserDto;
import org.prj.user.web.v1.entity.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
