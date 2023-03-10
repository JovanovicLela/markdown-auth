package com.doc.auth.services.impl;

import com.doc.auth.daos.RoleDAO;
import com.doc.auth.dtos.RoleInfoDTO;
import com.doc.auth.models.MarkdownRoleModel;
import com.doc.auth.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void createRole(RoleInfoDTO roleInfoDTO) {

        MarkdownRoleModel markdownRoleModel = modelMapper.map(roleInfoDTO, MarkdownRoleModel.class);
        roleDAO.save(markdownRoleModel);

        modelMapper.map(markdownRoleModel, roleInfoDTO);
    }

    @Override
    public RoleInfoDTO getRole(String roleId) {

        Optional<MarkdownRoleModel> markdownRoleModelOptional = roleDAO.findById(roleId);

        if (markdownRoleModelOptional.isPresent()) {
            final MarkdownRoleModel markdownRoleModel = markdownRoleModelOptional.get();
            return modelMapper.map(markdownRoleModel, RoleInfoDTO.class);
        }

        return null;
    }
}
