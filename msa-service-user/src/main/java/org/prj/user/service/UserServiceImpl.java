package org.prj.user.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.prj.user.dto.UserDto;
import org.prj.user.entity.UserEntity;
import org.prj.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        //TODO SpringSecurity 적용
        userEntity.setEncryptedPwd(UUID.randomUUID().toString());

        userRepository.save(userEntity);
        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);
        return returnUserDto;
    }
}
