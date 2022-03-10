package com.startext.testtask.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    private UUID id = UUID.randomUUID();
    private String userId;
    private String content;

    @ManyToOne
    @JoinColumn(name = "artifact_id")
    private ArtifactEntity artifact;

    @Transient
    private List<CommentEntity> previousVersions = new ArrayList<>();

    public CommentEntity() {
    }

    public ArtifactEntity getArtifact() {
        return artifact;
    }

    public void setArtifact(ArtifactEntity artifact) {
        this.artifact = artifact;
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

    @Transient
    public List<CommentEntity> getPreviousVersions() {
        return previousVersions;
    }

    @Transient
    public void setPreviousVersions(List<CommentEntity> previousVersions) {
        this.previousVersions = previousVersions;
    }

    @Transient
    public void rememberThisVersion() {
        previousVersions.add(clone());
    }

    public CommentEntity clone() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.id = new UUID(id.getMostSignificantBits(), id.getLeastSignificantBits());
        commentEntity.userId = userId + "";
        commentEntity.content = content + "";
        commentEntity.artifact = artifact;
        return  commentEntity;
    }
}
