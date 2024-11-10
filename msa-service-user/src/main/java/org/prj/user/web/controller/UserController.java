package org.prj.user.web.controller;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.prj.user.web.client.feign.OrderServiceClient;
import org.prj.user.web.dto.ResponseOrderDTO;
import org.prj.user.web.dto.UserDTO;
import org.prj.user.web.entity.UserEntity;
import org.prj.user.web.service.UserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final Environment env;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final OrderServiceClient orderClient;

    // 서비스 상태 확인
    @GetMapping("/status")
    public String status() {
        return String.format("It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", gateway ip=" + env.getProperty("gateway.ip")
                + ", message=" + env.getProperty("greeting.message")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }

    // 모든 사용자 조회
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllMembers() {
        Iterable<UserEntity> memberList = userService.getAllMembers();
        List<UserDTO> result = new ArrayList<>();
        memberList.forEach(i ->
                result.add(UserDTO.createUserDTO(i.getEmail(), i.getName(), null)));

        return ResponseEntity.status(OK).body(result);
    }

    // 특정 사용자 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getMember(@PathVariable("userId") String userId) {
        UserEntity findUser = userService.getMemberByUserId(userId).orElse(null);

        if (findUser == null) {
            return ResponseEntity.status(NOT_FOUND).build();
        }

        //#1, REST TEMPLATE CALL
//        String orderUrl = String.format(env.getProperty("order-service.url"),userId);
//        ResponseEntity<List<ResponseOrderDTO>> orderListResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<ResponseOrderDTO>>() {
//                });
//
//        List<ResponseOrderDTO> ordersList = orderListResponse.getBody();

        //#2, OPEN FEIGN CALL
        /* Feign exception handling */
//        List<ResponseOrderDTO> ordersList = null;
//        try {
//            orderClient.getOrders(userId);
//
//        } catch (FeignException ex) {
//            log.error(ex.getMessage());
//        }

        //#3, OPEN FEIGN CALL
        /* Feign exception handling2 */
        List<ResponseOrderDTO> ordersList = orderClient.getOrders(userId);
        UserDTO result = UserDTO.createUserDTO(findUser.getEmail(), findUser.getName(), ordersList);

        return ResponseEntity.status(OK).body(result);
    }

    // 새 사용자 생성
    @PostMapping
    public ResponseEntity<UserDTO> createMember(@RequestBody UserDTO userDTO) {
        UserEntity user = UserEntity.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .encryptedPwd(passwordEncoder.encode(userDTO.getPassword()))
                .userId(randomUUID().toString())
                .build();

        UserEntity savedUser = userService.createMember(user).orElse(null);
        if (savedUser == null) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

        UserDTO result = UserDTO.createUserDTO(savedUser.getEmail(), savedUser.getName(), null);
        return ResponseEntity.status(CREATED).body(result);
    }

}
