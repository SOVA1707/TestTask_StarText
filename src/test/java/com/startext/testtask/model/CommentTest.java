package com.startext.testtask.model;

import com.startext.testtask.Factory;
import com.startext.testtask.entity.CommentEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void toModel() {
        assertDoesNotThrow(() -> {
            CommentEntity commentEntity = Factory.getCommentEntity();
            Comment comment = Comment.toModel(commentEntity);
            assertNotNull(comment, "Comment = null.");
            assertNotNull(comment.getContent(), "Content = null.");
        }, "Throw exception.");
    }
}