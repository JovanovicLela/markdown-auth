package com.doc.auth.initialise;

import com.doc.auth.daos.RoleDAO;
import com.doc.auth.daos.UserDAO;
import com.doc.auth.models.MarkdownRoleModel;
import com.doc.auth.models.MarkdownUserModel;
import com.doc.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;

@Profile({"dev", "test"})
@Component
public class InitialiseTestData {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    TokenService tokenService;


    @EventListener
    public void appReady(ApplicationReadyEvent applicationReadyEvent) {
        addRoles();
        addUsers();
    }

    private void addRoles() {
        roleDAO.deleteAll();

        MarkdownRoleModel markdownRoleModel1 = new MarkdownRoleModel();
        markdownRoleModel1.setRole("ADMIN");

        MarkdownRoleModel markdownRoleModel2 = new MarkdownRoleModel();
        markdownRoleModel2.setRole("USER");

        roleDAO.save(markdownRoleModel1);
        roleDAO.save(markdownRoleModel2);
    }

    private void addUsers() {

        userDAO.deleteAll();

        MarkdownUserModel markdownUserModel1 = new MarkdownUserModel();
        markdownUserModel1.setUsername("admin");
        markdownUserModel1.setEmail("admin@gmail.com");
        markdownUserModel1.setPassword(bCryptPasswordEncoder.encode("admin"));
        markdownUserModel1.setRoles(Collections.singletonList("ADMIN"));
        tokenService.generateToken(markdownUserModel1);
        markdownUserModel1.setDisplayName("adminDisplayName");

        userDAO.save(markdownUserModel1);

        MarkdownUserModel markdownUserModel2 = new MarkdownUserModel();
        markdownUserModel2.setUsername("user");
        markdownUserModel2.setEmail("user@gmail.com");
        markdownUserModel2.setPassword(bCryptPasswordEncoder.encode("user"));
        markdownUserModel2.setRoles(Collections.singletonList("USER"));
        tokenService.generateToken(markdownUserModel2);
        markdownUserModel2.setDisplayName("userDisplayName");

        userDAO.save(markdownUserModel2);
    }


}
