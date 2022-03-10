package com.startext.testtask.controller;

import com.startext.testtask.Factory;
import com.startext.testtask.model.CommentCreationDTO;
import com.startext.testtask.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(CommentService.class)
@DataJpaTest
class CommentControllerTest {

    @SpyBean
    private CommentController commentController;

    private static ResponseEntity response;

    private static CommentCreationDTO comment = new CommentCreationDTO();
    private static UUID id;

    @DisplayName("Create comment test")
    @Test
    void createComment() {
        response = commentController.createComment(comment);
        id = (UUID) response.getBody();
        checkOk();
        response = commentController.deleteComment(id);
        checkOk();
    }

    @DisplayName("Get comment test")
    @Test
    void getComment() {
        response = commentController.createComment(comment);
        id = (UUID) response.getBody();
        checkOk();
        response = commentController.getComment(id);
        checkOk();
        response = commentController.deleteComment(id);
        checkOk();
    }

    @DisplayName("Update comment test")
    @Test
    void updateComment() {
        response = commentController.createComment(comment);
        id = (UUID) response.getBody();
        checkOk();
        response = commentController.updateComment(id, Factory.getComment());
        checkOk();
        response = commentController.deleteComment(id);
        checkOk();
    }

    @DisplayName("Delete comment test")
    @Test
    void deleteComment() {
        response = commentController.createComment(comment);
        id = (UUID) response.getBody();
        checkOk();
        response = commentController.deleteComment(id);
        checkOk();
        response = commentController.deleteComment(id);
        checkBad();
    }

    private static void checkOk() {
        assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
    }

    private static void checkBad() {
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
    }
}