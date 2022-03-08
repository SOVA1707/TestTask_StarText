package com.startext.testtask.model;

import com.startext.testtask.entity.ArtifactEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArtifactTest {

    @Mock
    private ArtifactEntity artifactEntity;

    @Test
    void toModel() {
        assertDoesNotThrow(() -> {
            Artifact artifact = Artifact.toModel(artifactEntity);
            assertNotNull(artifact, "artifact = null.");
            assertNotNull(artifact.getUserId(), "UserId = null.");
            assertNotNull(artifact.getCategory(), "Category = null.");
            assertNotNull(artifact.getDescription(), "Description = null.");
        }, "Throw exception.");
    }
}