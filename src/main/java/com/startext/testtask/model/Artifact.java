package com.startext.testtask.model;

import com.startext.testtask.entity.ArtifactEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Artifact {
    private String userId;
    private String category;
    private String description;
    private List<Comment> comments = new ArrayList<>();

    public static Artifact toModel(ArtifactEntity artifact) {
        Artifact model = new Artifact();
        model.setUserId(artifact.getUserId());
        model.setCategory(artifact.getCategory());
        model.setDescription(artifact.getDescription());
        model.setComments(artifact.getComments().stream().map(Comment::toModel).collect(Collectors.toList()));
        return model;
    }

    public Artifact() {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
