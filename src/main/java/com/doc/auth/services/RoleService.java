package com.doc.auth.services;

import com.doc.auth.dtos.RoleInfoDTO;

public interface RoleService {

    void createRole(RoleInfoDTO roleInfoDTO);
    RoleInfoDTO getRole(String roleId);
}
