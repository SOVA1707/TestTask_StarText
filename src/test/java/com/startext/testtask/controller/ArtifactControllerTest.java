package com.startext.testtask.controller;

import com.startext.testtask.Factory;
import com.startext.testtask.entity.ArtifactEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtifactControllerTest {

    @Autowired
    private ArtifactController artifactController;

    private ArtifactEntity artifactEntity = Factory.getArtifactEntity();

    @Test
    void createArtifact() {
    }

    @Test
    void getArtifact() {
    }

    @Test
    void setArtifact() {
    }

    @Test
    void deleteArtifact() {
    }
}