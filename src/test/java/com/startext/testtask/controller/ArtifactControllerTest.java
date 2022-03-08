package com.startext.testtask.controller;

import com.startext.testtask.Factory;
import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.service.ArtifactService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(ArtifactService.class)
@DataJpaTest
class ArtifactControllerTest {

    @SpyBean
    private ArtifactController artifactController;

    private static ResponseEntity response;

    private static ArtifactEntity artifactEntity = Factory.getArtifactEntity();
    private static UUID id = artifactEntity.getId();

    @DisplayName("Create artifact test")
    @Test
    void createArtifact() {
        response = artifactController.createArtifact(artifactEntity);
        checkOk();
        response = artifactController.createArtifact(artifactEntity);
        checkBad();
        response = artifactController.deleteArtifact(id);
        checkOk();
    }

    @DisplayName("Get artifact test")
    @Test
    void getArtifact() {
        response = artifactController.createArtifact(artifactEntity);
        checkOk();
        response = artifactController.getArtifact(id);
        checkOk();
        response = artifactController.deleteArtifact(id);
        checkOk();
    }

    @DisplayName("Update artifact test")
    @Test
    void updateArtifact() {
        response = artifactController.createArtifact(artifactEntity);
        checkOk();
        response = artifactController.updateArtifact(id, Factory.getArtifact());
        checkOk();
        response = artifactController.deleteArtifact(id);
        checkOk();
    }

    @DisplayName("Delete artifact test")
    @Test
    void deleteArtifact() {
        response = artifactController.createArtifact(artifactEntity);
        checkOk();
        response = artifactController.deleteArtifact(id);
        checkOk();
    }

    private static void checkOk() {
        assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
    }

    private static void checkBad() {
        assertEquals(response.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
    }
}