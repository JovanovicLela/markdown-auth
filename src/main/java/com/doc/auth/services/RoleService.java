package com.doc.auth.services;

import com.doc.auth.dtos.RoleInfoDTO;

public interface RoleService {

    RoleInfoDTO createRole(RoleInfoDTO roleInfoDTO);
    RoleInfoDTO getRole(String roleId);
}
