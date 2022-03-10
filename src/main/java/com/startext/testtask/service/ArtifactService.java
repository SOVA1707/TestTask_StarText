package com.startext.testtask.service;

import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.entity.CommentEntity;
import com.startext.testtask.exception.ArtifactAlreadyExistException;
import com.startext.testtask.exception.ArtifactNotFoundException;
import com.startext.testtask.model.Artifact;
import com.startext.testtask.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArtifactService {

    @Autowired
    private ArtifactRepository artifactRepository;

    public ArtifactEntity createArtifact(ArtifactEntity artifact) throws ArtifactAlreadyExistException {
        if (artifactRepository.findById(artifact.getId()).isEmpty())
            return artifactRepository.save(artifact);
        throw new ArtifactAlreadyExistException();
    }

    public Artifact getArtifact(UUID id) throws ArtifactNotFoundException {
        return Artifact.toModel(artifactRepository.findById(id).orElseThrow(ArtifactNotFoundException::new));

    }

    public Artifact updateArtifact(UUID id, Artifact newArtifact) throws ArtifactNotFoundException {
        ArtifactEntity artifactEntity = artifactRepository.findById(id).orElseThrow(ArtifactNotFoundException::new);
        artifactEntity.rememberThisVersion();
        artifactEntity.setUserId(newArtifact.getUserId());
        artifactEntity.setCategory(newArtifact.getCategory());
        artifactEntity.setDescription(newArtifact.getDescription());
        artifactRepository.save(artifactEntity);
        return Artifact.toModel(artifactEntity);
    }

    public Artifact deleteArtifact(UUID id) throws ArtifactNotFoundException {
        ArtifactEntity artifactEntity = artifactRepository.findById(id).orElseThrow(ArtifactNotFoundException::new);
        artifactRepository.delete(artifactEntity);
        return Artifact.toModel(artifactEntity);
    }

    public List<Artifact> getAllArtifactsByCategory(String category) {
        List<Artifact> list = new ArrayList<>();
        artifactRepository.findAll().forEach(e -> {
            if (e.getCategory().equals(category))
                list.add(Artifact.toModel(e));
        });
        return list;
    }

    public List<Artifact> getAllArtifactsByUserId(String userId) {
        List<Artifact> list = new ArrayList<>();
        artifactRepository.findAll().forEach(e -> {
            if (e.getUserId().equals(userId))
                list.add(Artifact.toModel(e));
        });
        return list;
    }

    public List<Artifact> getAllArtifactsByDescription(String description) {
        List<Artifact> list = new ArrayList<>();
        artifactRepository.findAll().forEach(e -> {
            if (e.getDescription().equals(description))
                list.add(Artifact.toModel(e));
        });
        return list;
    }

    public List<Artifact> getAllArtifactsByCommentContent(String content) {
        List<Artifact> list = new ArrayList<>();
        artifactRepository.findAll().forEach(e -> {
            for (CommentEntity comment : e.getComments()) {
                if (comment.getContent().equals(content)) {
                    list.add(Artifact.toModel(e));
                }
            }
        });
        return list;
    }

    public List<ArtifactEntity> getPreviousVersions(UUID id) throws ArtifactNotFoundException {
        Optional<ArtifactEntity> optionalArtifact = artifactRepository.findById(id);
        if (optionalArtifact.isPresent()) {
            return optionalArtifact.get().getPreviousVersions();
        } else {
            throw new ArtifactNotFoundException();
        }
    }

    public void sortByCreated(List<ArtifactEntity> list) {
        list.sort(Comparator.comparing(ArtifactEntity::getCreated));
    }

    public void sortByCategory(List<ArtifactEntity> list) {
        list.sort(Comparator.comparing(ArtifactEntity::getCategory));
    }

    public void sortByUserId(List<ArtifactEntity> list) {
        list.sort(Comparator.comparing(ArtifactEntity::getUserId));
    }
}
