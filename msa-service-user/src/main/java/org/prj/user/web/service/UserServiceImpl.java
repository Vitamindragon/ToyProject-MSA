package org.prj.user.web.service;

import lombok.RequiredArgsConstructor;
import org.prj.user.web.entity.UserEntity;
import org.prj.user.web.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> createMember(UserEntity user) {

        UserEntity savedMember = userRepository.save(user);
        return ofNullable(savedMember);
    }

    @Override
    public Optional<UserEntity> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Iterable<UserEntity> findAllMembers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = findByEmail(email).get();

        if (user == null) {
            throw new UsernameNotFoundException(email + ": not found");
        }

        return new User(user.getEmail(), user.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }
}
