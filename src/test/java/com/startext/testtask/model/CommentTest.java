package com.startext.testtask.model;

import com.startext.testtask.entity.CommentEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Mock
    private CommentEntity commentEntity;

    @Test
    void toModel() {
        assertDoesNotThrow(() -> {
            Comment comment = Comment.toModel(commentEntity);
            assertNotNull(comment, "Comment = null.");
            assertNotNull(comment.getContent(), "Content = null.");
        }, "Throw exception.");
    }
}