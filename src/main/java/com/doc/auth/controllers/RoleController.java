package com.doc.auth.controllers;

import com.doc.auth.dtos.RoleInfoDTO;
import com.doc.auth.services.RoleService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Preconditions.checkNotNull;

@RestController
@RequestMapping("/role")
@PreAuthorize("hasAnyRole('ADMIN')")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/create")
    public RoleInfoDTO createRole(@RequestBody RoleInfoDTO roleInfoDTO) {

        checkNotNull(roleInfoDTO);
        roleService.createRole(roleInfoDTO);
        return roleInfoDTO;
    }

    @GetMapping("/info/{roleId}")
    public RoleInfoDTO getRoleInfo(@PathVariable String roleId) {
        return roleService.getRole(roleId);
    }
}
