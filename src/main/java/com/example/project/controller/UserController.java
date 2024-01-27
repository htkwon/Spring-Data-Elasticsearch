package com.example.project.controller;

import com.example.project.dto.UserDto;
import com.example.project.dto.UserJoinDto;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public ResponseEntity<UserDto> join(@RequestBody UserJoinDto dto){
        UserDto res = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }



}
