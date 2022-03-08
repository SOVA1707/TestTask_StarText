package com.startext.testtask.model;

import com.startext.testtask.entity.ArtifactEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static com.startext.testtask.Factory.getArtifactEntity;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArtifactTest {

    @Test
    void toModel() {
        assertDoesNotThrow(() -> {
            ArtifactEntity artifactEntity = getArtifactEntity();
            Artifact artifact = Artifact.toModel(artifactEntity);
            assertNotNull(artifact, "artifact = null.");
            assertNotNull(artifact.getUserId(), "UserId = null.");
            assertNotNull(artifact.getCategory(), "Category = null.");
            assertNotNull(artifact.getDescription(), "Description = null.");
        }, "Throw exception.");
    }
}