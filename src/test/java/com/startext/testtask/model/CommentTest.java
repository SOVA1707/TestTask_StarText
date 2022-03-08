package com.startext.testtask.model;

import com.startext.testtask.Factory;
import com.startext.testtask.entity.CommentEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommentTest {

    @DisplayName("To model")
    @Test
    void toModel() {
        assertDoesNotThrow(() -> {
            CommentEntity commentEntity = Factory.getCommentEntity();
            Comment comment = Comment.toModel(commentEntity);
            assertNotNull(comment, "Comment = null.");
            assertNotNull(comment.getContent(), "Content = null.");
        }, "Throw exception");
    }
}