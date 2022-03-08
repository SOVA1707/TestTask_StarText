package com.startext.testtask.repository;

import com.startext.testtask.entity.ArtifactEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ArtifactRepository extends CrudRepository<ArtifactEntity, UUID> {
    ArtifactEntity findBySuuid(String suuid);
}
