package org.scraper.foodstagram.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("member")
@Data
public class Member {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean isActive;

    private String password;

    private Instant birthDate;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
