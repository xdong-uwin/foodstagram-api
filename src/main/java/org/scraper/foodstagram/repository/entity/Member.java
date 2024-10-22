package org.scraper.foodstagram.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
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
