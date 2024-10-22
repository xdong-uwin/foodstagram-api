package org.scraper.foodstagram.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class MemberDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean isActive;

    private String password;

    private Instant birthDate;

}
