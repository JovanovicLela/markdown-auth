package com.doc.auth.controllers;

import com.doc.auth.dtos.RoleInfoDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @PostMapping("/create")
    public RoleInfoDTO createRole(@RequestBody RoleInfoDTO roleInfoDTO) {

        System.out.println(roleInfoDTO.getRole());

        // TODO: create service to handle creation logic

        return null;
    }

    @GetMapping("/info/{roleId}")
    public RoleInfoDTO getRoleInfo(@PathVariable String roleId) {

        System.out.println("Get info for role: " + roleId);

        // TODO: create service to handle creation logic

        return null;
    }
}
