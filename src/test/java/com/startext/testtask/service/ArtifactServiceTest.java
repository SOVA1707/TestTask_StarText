package com.startext.testtask.service;

import com.startext.testtask.Factory;
import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.exception.ArtifactAlreadyExistException;
import com.startext.testtask.exception.ArtifactNotFoundException;
import com.startext.testtask.model.Artifact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArtifactServiceTest {

    @SpyBean
    private ArtifactService artifactService;

    private static ArtifactEntity artifactEntity = Factory.getArtifactEntity();

    @DisplayName("Create artifact test")
    @Test
    void createArtifact() {
        assertDoesNotThrow(() -> {
            artifactService.createArtifact(artifactEntity);
        }, "Throw exception.");
        assertThrows(ArtifactAlreadyExistException.class, () -> artifactService.createArtifact(artifactEntity), "Artifact created twice");
    }

    @DisplayName("Get artifact test")
    @Test
    void getArtifact() {
        assertDoesNotThrow(() -> {
            artifactService.createArtifact(artifactEntity);
            Artifact artifact = artifactService.getArtifact(artifactEntity.getId());
            assertNotNull(artifact, "Artifact == null");
        }, "Throw exception");
    }

    @DisplayName("Update artifact test")
    @Test
    void updateArtifact() {
        assertDoesNotThrow(() -> {
            Artifact newArtifact = Factory.getArtifact();
            String newId = newArtifact.getUserId();
            String newCategory = newArtifact.getCategory();
            String newDescription = newArtifact.getDescription();

            artifactService.createArtifact(artifactEntity);

            Artifact artifact = artifactService.updateArtifact(artifactEntity.getId(), newArtifact);

            assertNotNull(artifact, "Artifact == null");
            assertEquals(artifact.getUserId(), newId, "Id != " + newId);
            assertEquals(artifact.getCategory(), newCategory, "Category != " + newCategory);
            assertEquals(artifact.getDescription(), newDescription, "Description != " + newDescription);
        }, "Throw exception");
    }

    @DisplayName("Delete artifact test")
    @Test
    void deleteArtifact() {
        assertDoesNotThrow(() -> {
            artifactService.createArtifact(artifactEntity);
            artifactService.deleteArtifact(artifactEntity.getId());
            assertThrows(ArtifactNotFoundException.class, () -> artifactService.getArtifact(artifactEntity.getId()), "Artifact was found after remove");
        }, "Throw exception.");
    }
}