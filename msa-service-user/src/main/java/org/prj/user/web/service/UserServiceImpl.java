package org.prj.user.web.service;

import lombok.RequiredArgsConstructor;
import org.prj.user.web.dto.UserDTO;
import org.prj.user.web.entity.User;
import org.prj.user.web.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static java.util.Optional.*;
import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public Optional<User> createMember(User user) {

        User savedMember = userRepository.save(user);
        return ofNullable(savedMember);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Iterable<User> findAllMembers() {
        return userRepository.findAll();
    }

}
