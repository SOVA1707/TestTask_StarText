package com.startext.testtask.service;

import com.startext.testtask.entity.ArtifactEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DataJpaTest
class ArtifactServiceTest {

//    @Autowired
//    private ArtifactService artifactService;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Create artifact test")
    @Test
    void createArtifact() {
        ArtifactEntity artifactEntity = entityManager.persist(new ArtifactEntity());
        assertDoesNotThrow(() -> {
//            artifactService.createArtifact(artifactEntity);
        });
    }

    @DisplayName("Get artifact test")
    @Test
    void getArtifact() {
    }

    @DisplayName("Update artifact test")
    @Test
    void updateArtifact() {
    }

    @DisplayName("Delete artifact test")
    @Test
    void deleteArtifact() {
    }
}