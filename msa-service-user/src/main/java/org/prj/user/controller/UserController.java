package org.prj.user.controller;

import lombok.RequiredArgsConstructor;
import org.prj.user.vo.Greeting;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final Environment env;
    private final Greeting greeting;


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



}
