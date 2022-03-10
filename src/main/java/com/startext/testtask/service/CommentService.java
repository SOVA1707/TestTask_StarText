package com.startext.testtask.service;

import com.startext.testtask.entity.CommentEntity;
import com.startext.testtask.exception.CommentAlreadyExistException;
import com.startext.testtask.exception.CommentNotFoundException;
import com.startext.testtask.model.Comment;
import com.startext.testtask.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity createComment(CommentEntity comment) throws CommentAlreadyExistException {
        if (commentRepository.findById(comment.getId()).isEmpty())
            return commentRepository.save(comment);
        throw new CommentAlreadyExistException();
    }

    public Comment getComment(UUID id) throws CommentNotFoundException {
        return Comment.toModel(commentRepository.findById(id).orElseThrow(CommentNotFoundException::new));
    }

    public Comment updateComment(UUID id, Comment newComment) throws CommentNotFoundException {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        commentEntity.rememberThisVersion();
        commentEntity.setContent(newComment.getContent());
        commentRepository.save(commentEntity);
        return Comment.toModel(commentEntity);
    }

    public Comment deleteComment(UUID id) throws CommentNotFoundException {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(commentEntity);
        return Comment.toModel(commentEntity);
    }

    public List<CommentEntity> getPreviousVersions(UUID id) throws CommentNotFoundException {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new).getPreviousVersions();
    }
}
