package com.startext.testtask.service;

import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.repository.ArtifactRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class ArtifactServiceMockTest {

    @Mock
    private ArtifactRepository artifactRepository;

    @InjectMocks
    private ArtifactService artifactService = new ArtifactService();

    @Mock
    private TestEntityManager entityManager;

    @Test
    void testGet() {
        ArtifactEntity artifactEntity = entityManager.persistAndFlush(new ArtifactEntity());
        System.out.println(artifactEntity);
        assertDoesNotThrow(() -> {
            artifactService.createArtifact(artifactEntity);
        });

    }
}
