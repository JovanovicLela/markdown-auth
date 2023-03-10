package com.doc.auth.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoDTO {

    private String id;
    private String username;
    private String displayName;
    private String password;
    private String email;
    private Date dateCreated;
    private Date dateUpdated;
    private List<String> roles;



}
