package com.startext.testtask.service;

import com.startext.testtask.Factory;
import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.entity.CommentEntity;
import com.startext.testtask.exception.CommentAlreadyExistException;
import com.startext.testtask.exception.CommentNotFoundException;
import com.startext.testtask.model.Artifact;
import com.startext.testtask.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentServiceTest {

    @SpyBean
    private CommentService commentService;

    private static CommentEntity commentEntity = Factory.getCommentEntity();
    private static UUID id = commentEntity.getId();

    @DisplayName("Create comment test")
    @Test
    void createComment() {
        assertDoesNotThrow(() -> {
            commentService.createComment(commentEntity);
        }, "Throw exception.");
        assertThrows(CommentAlreadyExistException.class, () -> commentService.createComment(commentEntity), "Comment create twice");
    }

    @DisplayName("Get comment test")
    @Test
    void getComment() {
        assertDoesNotThrow(() -> {
            commentService.createComment(commentEntity);
            Comment comment = commentService.getComment(id);
            assertNotNull(comment, "Comment == null");
        }, "Throw exception");
    }

    @DisplayName("Update comment test")
    @Test
    void updateComment() {
        assertDoesNotThrow(() -> {
            Comment newComment = Factory.getComment();
            String newContent = newComment.getContent();

            assertThrows(CommentNotFoundException.class, () -> commentService.updateComment(id, newComment), "Comment was found on update");

            commentService.createComment(commentEntity);

            Comment comment = commentService.updateComment(id, newComment);

            assertNotNull(comment, "Comment == null");
            assertEquals(comment.getContent(), newContent);
        }, "Throw exception");
    }

    @DisplayName("Delete comment test")
    @Test
    void deleteComment() {
        assertDoesNotThrow(() -> {
            commentService.createComment(commentEntity);
            commentService.deleteComment(id);
            assertThrows(CommentNotFoundException.class, () -> commentService.getComment(id), "Comment was found after delete");
        }, "Throw exception");
    }

    @DisplayName("Check version control")
    @Test
    void versionControlCheck() {
        assertDoesNotThrow(() -> {
            CommentEntity comment1 = Factory.getCommentEntity();
            CommentEntity comment2 = Factory.getCommentEntity();
            CommentEntity comment3 = Factory.getCommentEntity();
            List<CommentEntity> comments = new ArrayList<>(Arrays.asList(comment1, comment2, comment3));

            commentService.createComment(commentEntity);

            for (CommentEntity comment : comments) {
                commentService.updateComment(commentEntity.getId(), Comment.toModel(comment));
            }
            comments.add(commentEntity);

            List<CommentEntity> previous = commentService.getPreviousVersions(commentEntity.getId());

            for (CommentEntity prev : previous) {
                boolean check = false;
                for (CommentEntity comment : comments) {
                    if (prev.getUserId().equals(comment.getUserId())) {
                        check = true;
                        break;
                    }
                }
                assertTrue(check);
            }
        }, "Throw exception.");
    }
}