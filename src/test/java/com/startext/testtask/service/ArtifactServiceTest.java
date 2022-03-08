package com.startext.testtask.service;

import com.startext.testtask.Factory;
import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.entity.CommentEntity;
import com.startext.testtask.exception.ArtifactAlreadyExistException;
import com.startext.testtask.exception.ArtifactNotFoundException;
import com.startext.testtask.model.Artifact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.*;

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

    @DisplayName("Get all artifacts tests")
    @Test
    void getAllArtifactsBy() {
        String category = "category 1";
        String userId = "1234";
        String description = "my description";
        String content = "my content";

        CommentEntity commentEntity1 = Factory.getCommentEntity();
        commentEntity1.setContent("AINFWA");
        CommentEntity commentEntity2 = Factory.getCommentEntity();
        commentEntity2.setContent(content);
        CommentEntity commentEntity3 = Factory.getCommentEntity();
        commentEntity3.setContent("Fassrq 14");

        List<CommentEntity> comments = new ArrayList<>(Arrays.asList(commentEntity1, commentEntity2, commentEntity3));

        ArtifactEntity artifactEntity1 = Factory.getArtifactEntity();
        artifactEntity1.setCategory(category);
        artifactEntity1.setUserId(userId);
        artifactEntity1.setComments(new ArrayList<>(comments));
        ArtifactEntity artifactEntity2 = Factory.getArtifactEntity();
        artifactEntity2.setCategory(category);
        artifactEntity2.setUserId(userId);
        artifactEntity2.setDescription(description);
        artifactEntity2.setComments(new ArrayList<>(comments));
        ArtifactEntity artifactEntity3 = Factory.getArtifactEntity();
        artifactEntity3.setCategory(category);
        artifactEntity3.setComments(new ArrayList<>(comments));
        ArtifactEntity artifactEntity4 = Factory.getArtifactEntity();
        artifactEntity4.setCategory("category 4");
        artifactEntity4.setComments(new ArrayList<>(comments));

        assertDoesNotThrow(() -> {
            artifactService.createArtifact(artifactEntity1);
            artifactService.createArtifact(artifactEntity2);
            artifactService.createArtifact(artifactEntity3);
            artifactService.createArtifact(artifactEntity4);
        }, "Throw error");

        List<Artifact> list;
        list = artifactService.getAllArtifactsByCategory(category);
        assertEquals(list.size(), 3);

        list = artifactService.getAllArtifactsByUserId(userId);
        assertEquals(list.size(), 2);

        list = artifactService.getAllArtifactsByDescription(description);
        assertEquals(list.size(), 1);

        list = artifactService.getAllArtifactsByCommentContent(content);
        assertEquals(list.size(), 4);
    }

    @DisplayName("Check version control")
    @Test
    void versionControlCheck() {
        assertDoesNotThrow(() -> {
            ArtifactEntity artifact1 = Factory.getArtifactEntity();
            ArtifactEntity artifact2 = Factory.getArtifactEntity();
            ArtifactEntity artifact3 = Factory.getArtifactEntity();
            List<ArtifactEntity> artifacts = new ArrayList<>(Arrays.asList(artifact1, artifact2, artifact3));

            artifactService.createArtifact(artifactEntity);

            for (ArtifactEntity artifact : artifacts) {
                artifactService.updateArtifact(artifactEntity.getId(), Artifact.toModel(artifact));
            }
            artifacts.add(artifactEntity);

            List<ArtifactEntity> previous = artifactService.getPreviousVersions(artifactEntity.getId());

            for (ArtifactEntity prev : previous) {
                boolean check = false;
                for (ArtifactEntity artifact : artifacts) {
                    if (prev.getUserId().equals(artifact.getUserId())) {
                        check = true;
                        break;
                    }
                }
                assertTrue(check);
            }
        }, "Throw exception.");
    }

    @DisplayName("Sort artifacts test")
    @Test
    void sortArtifacts() throws InterruptedException {
        ArtifactEntity artifactEntity1 = Factory.getArtifactEntity();
        artifactEntity1.setCategory("category 1");
        Thread.sleep(100);
        ArtifactEntity artifactEntity2 = Factory.getArtifactEntity();
        artifactEntity2.setCategory("category asd");
        Thread.sleep(100);
        ArtifactEntity artifactEntity3 = Factory.getArtifactEntity();
        artifactEntity3.setCategory("category 15");
        Thread.sleep(100);
        ArtifactEntity artifactEntity4 = Factory.getArtifactEntity();
        artifactEntity4.setCategory("category 4");

        List<ArtifactEntity> artifacts = new ArrayList<>();
        artifacts.add(artifactEntity3);
        artifacts.add(artifactEntity1);
        artifacts.add(artifactEntity4);
        artifacts.add(artifactEntity2);

        List<ArtifactEntity> sortedArtifacts = new ArrayList<>(artifacts);

        sortedArtifacts.sort(Comparator.comparing(ArtifactEntity::getCreated));
        artifactService.sortByCreated(artifacts);

        System.out.println("---Created---");
        for (int i = 0; i < artifacts.size(); i++) {
            ArtifactEntity artifact = artifacts.get(i);
            ArtifactEntity sortedArtifact = sortedArtifacts.get(i);
            System.out.println(artifact.getCreated());
            assertEquals(artifact.getCreated(), sortedArtifact.getCreated());
        }

        sortedArtifacts.sort(Comparator.comparing(ArtifactEntity::getCategory));
        artifactService.sortByCategory(artifacts);

        System.out.println("---Category---");
        for (int i = 0; i < artifacts.size(); i++) {
            ArtifactEntity artifact = artifacts.get(i);
            ArtifactEntity sortedArtifact = sortedArtifacts.get(i);
            System.out.println(artifact.getCategory());
            assertEquals(artifact.getCategory(), sortedArtifact.getCategory());
        }

        sortedArtifacts.sort(Comparator.comparing(ArtifactEntity::getUserId));
        artifactService.sortByUserId(artifacts);

        System.out.println("---UserId---");
        for (int i = 0; i < artifacts.size(); i++) {
            ArtifactEntity artifact = artifacts.get(i);
            ArtifactEntity sortedArtifact = sortedArtifacts.get(i);
            System.out.println(artifact.getUserId());
            assertEquals(artifact.getCategory(), sortedArtifact.getCategory());
        }
    }
}