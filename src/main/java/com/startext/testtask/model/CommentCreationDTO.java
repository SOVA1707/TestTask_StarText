package com.startext.testtask.model;

import com.startext.testtask.entity.CommentEntity;

import java.util.UUID;

public class CommentCreationDTO {
    private String userId;
    private String content;
    private UUID artifactId;

    public CommentCreationDTO() {
    }

    public static CommentEntity fromModel(CommentCreationDTO model) {
        CommentEntity comment = new CommentEntity();
        comment.setUserId(model.userId);
        comment.setContent(model.getContent());
//        comment.setArtifact(model.getArtifactId());
        return comment;
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

    public UUID getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(UUID artifactId) {
        this.artifactId = artifactId;
    }
}
