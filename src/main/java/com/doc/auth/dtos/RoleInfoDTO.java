package com.doc.auth.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class RoleInfoDTO {

    private String role;
    private Date dateCreated;
    private Date dateUpdated;
}
