package org.prj.user.web.controller;

import lombok.RequiredArgsConstructor;
import org.prj.user.web.dto.UserDTO;
import org.prj.user.web.entity.User;
import org.prj.user.web.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
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
        return String.format("It's Working in User Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllMembers() {
        Iterable<User> memberList = userService.findAllMembers();
        List<UserDTO> result = new ArrayList<>();
        memberList.forEach(i ->
                result.add(UserDTO.createMemberDTO(i.getEmail(), i.getName(), null)));


        return ResponseEntity.status(OK).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getMember(@PathVariable("userId") String userId) {
        User findUser = userService.findByUserId(userId).get();

        UserDTO result = UserDTO.createMemberDTO(findUser.getEmail(), findUser.getName(), null);
        return ResponseEntity.status(OK).body(result);
    }


    @PostMapping
    public ResponseEntity<UserDTO> createMember(@RequestBody UserDTO userDTO) {

        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .encryptedPwd(passwordEncoder.encode(userDTO.getPwd()))
                .userId(randomUUID().toString())
                .build();

        User savedUser = userService.createMember(user).get();
        UserDTO result = UserDTO.createMemberDTO(savedUser.getEmail(), savedUser.getName(), null);
        return ResponseEntity.status(CREATED).body(result);
    }

}
