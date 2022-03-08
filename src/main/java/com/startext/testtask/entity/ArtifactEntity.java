package com.startext.testtask.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "artifacts")
public class ArtifactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime created;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    private String category;
    private String description;

    public ArtifactEntity() {
        created = LocalDateTime.now();
        System.out.println(created);
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getUserId() {
        return userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
