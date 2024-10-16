package org.prj.user.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.prj.user.dto.UserDto;
import org.prj.user.service.UserService;
import org.prj.user.vo.Greeting;
import org.prj.user.vo.RequestUser;
import org.prj.user.vo.ResponseUser;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.modelmapper.convention.MatchingStrategies.*;
import static org.springframework.http.HttpStatus.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;
    private final ModelMapper mapper;


    @GetMapping("/health-check")
    public String status(){
        return String.format("It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", gateway ip(env)=" + env.getProperty("gateway.ip")
                + ", message=" + env.getProperty("greeting.message"));
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response){
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user){
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(CREATED).body(responseUser);
    }


}
