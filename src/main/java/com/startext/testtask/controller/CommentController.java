package com.startext.testtask.controller;

import com.startext.testtask.entity.CommentEntity;
import com.startext.testtask.exception.CommentAlreadyExistException;
import com.startext.testtask.exception.CommentNotFoundException;
import com.startext.testtask.model.Comment;
import com.startext.testtask.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PutMapping
    public ResponseEntity createComment(@RequestBody CommentEntity commentEntity) {
        ResponseEntity response;
        try {
            commentService.createComment(commentEntity);
            response = ResponseEntity.ok("Comment successful created");
        } catch (CommentAlreadyExistException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error on create comment.");
        }
        return response;
    }

    @GetMapping
    public ResponseEntity getComment(@RequestParam UUID id) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(commentService.getComment(id));
        } catch (CommentNotFoundException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error on get comment.");
        }
        return response;
    }

    @PostMapping
    public ResponseEntity updateComment(@RequestParam UUID id, @RequestBody Comment newComment) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(commentService.updateComment(id, newComment));
        } catch (CommentNotFoundException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error on update comment.");
        }
        return response;
    }

    @DeleteMapping
    public ResponseEntity deleteComment(@RequestParam UUID id) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(commentService.deleteComment(id));
        } catch (CommentNotFoundException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error on get comment.");
        }
        return response;
    }
}
