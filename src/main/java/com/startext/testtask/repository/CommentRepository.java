package com.startext.testtask.repository;

import com.startext.testtask.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<CommentEntity, UUID> {
}
