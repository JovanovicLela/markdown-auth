package com.doc.auth.services;

import com.doc.auth.dtos.UserInfoDTO;

public interface UserService {

    UserInfoDTO createUser(UserInfoDTO userInfoDTO);
    UserInfoDTO getUser(UserInfoDTO userInfoDTO);
    UserInfoDTO loginUser(UserInfoDTO userInfoDTO);

}
