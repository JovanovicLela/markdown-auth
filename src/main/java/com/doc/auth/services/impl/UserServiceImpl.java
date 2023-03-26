package com.doc.auth.services.impl;

import com.doc.auth.daos.RoleDAO;
import com.doc.auth.daos.UserDAO;
import com.doc.auth.dtos.UserInfoDTO;
import com.doc.auth.dtos.UserLoginDTO;
import com.doc.auth.models.MarkdownRoleModel;
import com.doc.auth.models.MarkdownUserModel;
import com.doc.auth.services.TokenService;
import com.doc.auth.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserServiceImpl implements UserService {

    private static final String BAD_CREDENTIALS = "Unknown username or invalid password";

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserDAO userDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    TokenService tokenService;

    @Override
    public void createUser(UserInfoDTO userInfoDTO) {

        checkNotNull(userInfoDTO.getPassword());

        MarkdownUserModel markdownUserModel = modelMapper.map(userInfoDTO, MarkdownUserModel.class);

        markdownUserModel.setPassword(bCryptPasswordEncoder.encode(userInfoDTO.getPassword()));

        markdownUserModel.setRoles(
                roleDAO.findAll().stream().map(MarkdownRoleModel::getRole)
                        .filter(role -> role.contains("USER")).collect(Collectors.toList())
        );

        tokenService.generateToken(markdownUserModel);

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

            if (bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), markdownUserModel.getPassword())) {

                return modelMapper.map(markdownUserModel, UserInfoDTO.class);
            } else {
              throw new BadCredentialsException(BAD_CREDENTIALS);
            }
        } else {
            throw new BadCredentialsException(BAD_CREDENTIALS);
        }
    }
}
