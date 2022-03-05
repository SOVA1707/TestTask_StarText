package com.startext.testtask.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID artifactId;
    private String userId;
    private String content;

    public CommentEntity(UUID artifactId) {
        this.artifactId = artifactId;
//        userId =
    }

    public UUID getId() {
        return id;
    }

    public UUID getArtifactId() {
        return artifactId;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
