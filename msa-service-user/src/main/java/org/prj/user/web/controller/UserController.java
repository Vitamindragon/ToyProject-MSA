package org.prj.user.web.controller;

import lombok.RequiredArgsConstructor;
import org.prj.user.web.dto.UserDTO;
import org.prj.user.web.entity.UserEntity;
import org.prj.user.web.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final Environment env;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;



    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", gateway ip=" + env.getProperty("gateway.ip")
                + ", message=" + env.getProperty("greeting.message")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllMembers() {
        Iterable<UserEntity> memberList = userService.findAllMembers();
        List<UserDTO> result = new ArrayList<>();
        memberList.forEach(i ->
                result.add(UserDTO.createMemberDTO(i.getEmail(), i.getName(), null)));


        return ResponseEntity.status(OK).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getMember(@PathVariable("userId") String userId) {
        UserEntity findUser = userService.findByUserId(userId).get();

        UserDTO result = UserDTO.createMemberDTO(findUser.getEmail(), findUser.getName(), null);
        return ResponseEntity.status(OK).body(result);
    }


    @PostMapping
    public ResponseEntity<UserDTO> createMember(@RequestBody UserDTO userDTO) {

        UserEntity user = UserEntity.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .encryptedPwd(passwordEncoder.encode(userDTO.getPassword()))
                .userId(randomUUID().toString())
                .build();

        UserEntity savedUser = userService.createMember(user).get();
        UserDTO result = UserDTO.createMemberDTO(savedUser.getEmail(), savedUser.getName(), null);
        return ResponseEntity.status(CREATED).body(result);
    }

}
