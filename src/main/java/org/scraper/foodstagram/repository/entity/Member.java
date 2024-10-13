package org.scraper.foodstagram.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("member")
public class Member {

    @Id
    private String id;
}
