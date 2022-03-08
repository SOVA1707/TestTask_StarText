package com.startext.testtask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "artifacts")
public class ArtifactEntity {
    @Id
    @JsonIgnore
    private UUID id = UUID.randomUUID();
    @JsonIgnore
    private LocalDateTime created;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    private String category;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artifact")
    private List<CommentEntity> comments = new ArrayList<>();

    @Transient
    private List<ArtifactEntity> previousVersions = new ArrayList<>();

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public ArtifactEntity() {
        created = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Transient
    public List<ArtifactEntity> getPreviousVersions() {
        return previousVersions;
    }

    @Transient
    public void setPreviousVersions(List<ArtifactEntity> previousVersions) {
        this.previousVersions = previousVersions;
    }

    @Transient
    public void rememberThisVersion() {
        previousVersions.add(clone());
    }

    public ArtifactEntity clone() {
        ArtifactEntity artifactEntity = new ArtifactEntity();
        artifactEntity.id = new UUID(id.getMostSignificantBits(), id.getLeastSignificantBits());
        artifactEntity.created = LocalDateTime.of(created.toLocalDate(), created.toLocalTime());
        artifactEntity.userId = userId + "";
        artifactEntity.category = category + "";
        artifactEntity.description = description + "";
        artifactEntity.comments = new ArrayList<>(comments);
        return artifactEntity;
    }
}
