package com.startext.testtask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @JsonIgnore
    private UUID id = UUID.randomUUID();
    private String userId;
    private String content;

    @ManyToOne
    @JoinColumn(name = "artifact_id")
    private ArtifactEntity artifact;

    public CommentEntity() {
    }

    public ArtifactEntity getArtifact() {
        return artifact;
    }

    public void setArtifact(ArtifactEntity artifact) {
        this.artifact = artifact;
        this.userId = artifact.getUserId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
