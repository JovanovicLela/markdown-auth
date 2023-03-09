package com.doc.auth.services;

import com.doc.auth.dtos.UserInfoDTO;
import com.doc.auth.dtos.UserLoginDTO;

public interface UserService {

    UserInfoDTO createUser(UserInfoDTO userInfoDTO);
    UserInfoDTO getUser(String userId);
    UserInfoDTO loginUser(UserLoginDTO userLoginDTO);

}
