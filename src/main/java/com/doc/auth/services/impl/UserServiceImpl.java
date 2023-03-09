package com.doc.auth.services.impl;

import com.doc.auth.daos.UserDAO;
import com.doc.auth.dtos.UserInfoDTO;
import com.doc.auth.dtos.UserLoginDTO;
import com.doc.auth.models.MarkdownUserModel;
import com.doc.auth.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDAO userDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createUser(UserInfoDTO userInfoDTO) {

        MarkdownUserModel markdownUserModel = modelMapper.map(userInfoDTO, MarkdownUserModel.class);
        userDAO.save(markdownUserModel);

        modelMapper.map(markdownUserModel, userInfoDTO);
    }

    @Override
    public UserInfoDTO getUser(String userId) {
        Optional<MarkdownUserModel> optionalMarkdownUserModel =  userDAO.findById(userId);
        if (optionalMarkdownUserModel.isPresent()) {
            return modelMapper.map(optionalMarkdownUserModel.get(), UserInfoDTO.class);
        }
        return null;
    }

    @Override
    public UserInfoDTO loginUser(UserLoginDTO userLoginDTO) {
        Optional<MarkdownUserModel> optionalMarkdownUserModel =  userDAO.findByUsername(userLoginDTO.getUsername());
        if (optionalMarkdownUserModel.isPresent()) {
            MarkdownUserModel markdownUserModel = optionalMarkdownUserModel.get();
        }
        return null;
    }
}
