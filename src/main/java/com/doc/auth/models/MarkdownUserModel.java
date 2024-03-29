package com.doc.auth.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
@EqualsAndHashCode(callSuper = true)
public class MarkdownUserModel extends GenericModel{

    @Indexed(direction = IndexDirection.DESCENDING, unique = true)
    private String username;

    @Indexed(direction = IndexDirection.DESCENDING, unique = true)
    private String displayName;

    @Indexed(direction = IndexDirection.DESCENDING, unique = true)
    private String email;

    private String jwtToken;
    private String password;
    private List<String> roles;

    public MarkdownUserModel() {
        super();
    }
}
