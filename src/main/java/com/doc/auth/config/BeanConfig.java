package com.doc.auth.config;

import com.doc.auth.dtos.UserInfoDTO;
import com.doc.auth.models.MarkdownUserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        final TypeMap<MarkdownUserModel, UserInfoDTO> markdownUserModelUserInfoDTOTypeMap =  modelMapper.typeMap(MarkdownUserModel.class, UserInfoDTO.class);
       // markdownUserModelUserInfoDTOTypeMap.addMappings(mapping -> mapping.skip(UserInfoDTO::setPassword));

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
