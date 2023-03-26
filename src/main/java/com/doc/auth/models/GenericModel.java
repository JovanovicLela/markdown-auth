package com.doc.auth.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
public class GenericModel implements Serializable, Persistable<String> {

    @Id
    private String id;

    @CreatedDate
    private Date dateCreated;

    @LastModifiedDate
    private Date dateUpdated;

    @Override
    public boolean isNew() {
        return isNull(this.dateCreated);
    }

    public GenericModel() {
        this.id = UUID.randomUUID().toString();
    }
}
