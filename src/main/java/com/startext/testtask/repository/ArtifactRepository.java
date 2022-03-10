package com.startext.testtask.repository;

import com.startext.testtask.entity.ArtifactEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ArtifactRepository extends CrudRepository<ArtifactEntity, UUID> {
    List<ArtifactEntity> findAllByCategory(String category);
    List<ArtifactEntity> findAllByUserId(String userId);
    List<ArtifactEntity> findAllByDescription(String description);
    List<ArtifactEntity> findAllByCommentsContent(String content);
}
