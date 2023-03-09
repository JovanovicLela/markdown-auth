package com.doc.auth.services.impl;

import com.doc.auth.dtos.UserInfoDTO;
import com.doc.auth.dtos.UserLoginDTO;
import com.doc.auth.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserInfoDTO createUser(UserInfoDTO userInfoDTO) {
        return null;
    }

    @Override
    public UserInfoDTO getUser(String userId) {
        return null;
    }

    @Override
    public UserInfoDTO loginUser(UserLoginDTO userLoginDTO) {
        return null;
    }
}
