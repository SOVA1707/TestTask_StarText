package com.startext.testtask.service;

import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.entity.CommentEntity;
import com.startext.testtask.exception.ArtifactNotFoundException;
import com.startext.testtask.exception.CommentAlreadyExistException;
import com.startext.testtask.exception.CommentNotFoundException;
import com.startext.testtask.model.Comment;
import com.startext.testtask.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity createComment(CommentEntity comment) throws CommentAlreadyExistException {
        if (commentRepository.findById(comment.getId()).isEmpty()) {
            return commentRepository.save(comment);
        } else {
            throw new CommentAlreadyExistException();
        }
    }

    public Comment getComment(UUID id) throws CommentNotFoundException {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            return Comment.toModel(optionalComment.get());
        } else {
            throw new CommentNotFoundException();
        }
    }

    public Comment updateComment(UUID id, Comment newComment) throws CommentNotFoundException {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            CommentEntity commentEntity = optionalComment.get();
            commentEntity.rememberThisVersion();
            commentEntity.setContent(newComment.getContent());
            commentRepository.save(commentEntity);
            return Comment.toModel(commentEntity);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public Comment deleteComment(UUID id) throws CommentNotFoundException {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(id);
            return Comment.toModel(optionalComment.get());
        } else {
            throw new CommentNotFoundException();
        }
    }

    public List<CommentEntity> getPreviousVersions(UUID id) throws CommentNotFoundException {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            return optionalComment.get().getPreviousVersions();
        } else {
            throw new CommentNotFoundException();
        }
    }
}
