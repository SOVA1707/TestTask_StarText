package com.startext.testtask.service;

import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.exception.ArtifactAlreadyExistException;
import com.startext.testtask.exception.ArtifactNotFoundException;
import com.startext.testtask.model.Artifact;
import com.startext.testtask.repository.ArtifactRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArtifactServiceTest {

    @Autowired
    private ArtifactRepository artifactRepository;

    @Mock
    private ArtifactService artifactService;

    @Mock
    private ArtifactEntity artifactEntity1;

    @Mock
    private ArtifactEntity artifactEntity2;

    @BeforeAll
    static void init() {
    }

    @DisplayName("Create artifact test")
    @Test
    void createArtifact() {
        System.out.println("Entity:");
        System.out.println(artifactEntity1);
        System.out.println(artifactEntity1.getId());
        assertDoesNotThrow(() -> {
            artifactService.createArtifact(artifactEntity1);
        }, "Throw exception.");
        assertThrows(ArtifactAlreadyExistException.class, () -> {
            artifactService.createArtifact(artifactEntity1);
        }, "Artifact successful create.");
    }

    @DisplayName("Get artifact test")
    @Test
    void getArtifact() {
        assertDoesNotThrow(() -> {
            artifactService.createArtifact(artifactEntity1);
            Artifact artifact = artifactService.getArtifact(artifactEntity1.getId());
            assertNotNull(artifact, "artifact = null");
        }, "Throw exception.");
        assertThrows(ArtifactNotFoundException.class, () -> {
            Artifact artifact = artifactService.getArtifact(artifactEntity2.getId());
        }, "Artifact was found.");
    }

    @DisplayName("Update artifact test")
    @Test
    void updateArtifact() {
        assertDoesNotThrow(() -> {
            Artifact newArtifact = new Artifact();
            String newId = "123";
            newArtifact.setUserId("123");
            String newCategory = "newCategory";
            newArtifact.setCategory(newCategory);
            String newDescription = "newDescription";
            newArtifact.setDescription(newDescription);

            artifactService.createArtifact(artifactEntity1);
            Artifact artifact = artifactService.updateArtifact(artifactEntity1.getId(), newArtifact);

            assertNotNull(artifact, "Artifact = null.");
            assertEquals(artifact.getUserId(), newId, "Id = null.");
            assertEquals(artifact.getCategory(), newCategory, "Category = null.");
            assertEquals(artifact.getDescription(), newDescription, "Description = null.");
        }, "Throw exception.");
        assertThrows(ArtifactNotFoundException.class, () -> {
            Artifact artifact = artifactService.updateArtifact(artifactEntity2.getId(), new Artifact());
        }, "Artifact was found.");
    }

    @DisplayName("Delete artifact test")
    @Test
    void deleteArtifact() {
        assertDoesNotThrow(() -> {
            artifactService.createArtifact(artifactEntity1);
            Artifact artifact = artifactService.deleteArtifact(artifactEntity1.getId());
            assertNotNull(artifact, "Artifact = null.");
        }, "Throw exception.");
        assertThrows(ArtifactNotFoundException.class, () -> {
            Artifact artifact = artifactService.deleteArtifact(artifactEntity2.getId());
        }, "Artifact was found.");
    }
}