package com.startext.testtask;

import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.entity.CommentEntity;
import com.startext.testtask.model.Artifact;
import com.startext.testtask.model.Comment;

import java.util.Random;
import java.util.UUID;

public class Factory {

    private static final Random rand = new Random(System.nanoTime());

    public static ArtifactEntity getArtifactEntity() {
        ArtifactEntity artifactEntity = new ArtifactEntity();
        artifactEntity.setId(UUID.randomUUID());
        artifactEntity.setUserId(String.valueOf(rand.nextInt()));
        artifactEntity.setCategory("Artifact category.");
        artifactEntity.setDescription("Artifact description.");
        return  artifactEntity;
    }

    public static CommentEntity getCommentEntity() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(getArtifactEntity().getId());
        commentEntity.setUserId(String.valueOf(rand.nextInt()));
        commentEntity.setContent("Comment " + commentEntity.getId() + " content.");
        return commentEntity;
    }

    public static Artifact getArtifact() {
        return Artifact.toModel(getArtifactEntity());
    }

    public static Comment getComment() {
        return Comment.toModel(getCommentEntity());
    }
}
