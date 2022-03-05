package com.startext.testtask.service;

import com.startext.testtask.entity.CommentEntity;
import com.startext.testtask.exception.CommentAlreadyExistException;
import com.startext.testtask.exception.CommentNotFoundException;
import com.startext.testtask.model.Comment;
import com.startext.testtask.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity createComment(CommentEntity entity) throws CommentAlreadyExistException {
        if (!commentRepository.existsById(entity.getId())) {
            return commentRepository.save(entity);
        } else {
            throw new CommentAlreadyExistException();
        }
    }

    public Comment getComment(UUID id) throws CommentNotFoundException {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            return Comment.toModel(optionalComment.get());
        }else {
            throw new CommentNotFoundException();
        }
    }

    public Comment updateComment(UUID id, Comment newComment) throws CommentNotFoundException {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            CommentEntity commentEntity = optionalComment.get();
            commentEntity.setContent(newComment.getContent());
            commentRepository.save(commentEntity);
            return Comment.toModel(commentEntity);
        }else {
            throw new CommentNotFoundException();
        }
    }

    public Comment deleteComment(UUID id) throws CommentNotFoundException {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(id);
            return Comment.toModel(optionalComment.get());
        }else {
            throw new CommentNotFoundException();
        }
    }

}
