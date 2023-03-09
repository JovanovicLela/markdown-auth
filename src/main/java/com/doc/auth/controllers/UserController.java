package com.doc.auth.controllers;

import com.doc.auth.dtos.UserInfoDTO;
import com.doc.auth.dtos.UserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Preconditions.checkNotNull;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/create")
    public UserInfoDTO createUser(@RequestBody UserInfoDTO userInfoDTO) {

        checkNotNull(userInfoDTO);

        System.out.println(userInfoDTO.getDisplayName());
        System.out.println(userInfoDTO.getRoles());

        //TODO: add service to handle logic

        return null;
    }

    @GetMapping("/info/{userId}")
    public UserInfoDTO getUserInfo(@PathVariable String userId) {

        System.out.println("Get info for user: " + userId);

        //TODO: add service to handle logic

        return null;
    }

    @PostMapping("/login")
    public UserLoginDTO loginUser(@RequestBody UserLoginDTO userLoginDTO) {

        checkNotNull(userLoginDTO);

        System.out.println(userLoginDTO.getUsername());
        System.out.println(userLoginDTO.getPassword());

        return null;
    }

}
