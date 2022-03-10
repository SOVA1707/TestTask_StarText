package com.startext.testtask.service;

import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.exception.ArtifactAlreadyExistException;
import com.startext.testtask.exception.ArtifactNotFoundException;
import com.startext.testtask.model.Artifact;
import com.startext.testtask.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArtifactService {

    @Autowired
    private ArtifactRepository artifactRepository;

    public ArtifactEntity createArtifact(ArtifactEntity artifact) throws ArtifactAlreadyExistException {
        if (artifactRepository.findById(artifact.getId()).isEmpty()) {
            return artifactRepository.save(artifact);
        } else {
            throw new ArtifactAlreadyExistException();
        }
    }

    public Artifact getArtifact(UUID id) throws ArtifactNotFoundException {
        Optional<ArtifactEntity> optionalArtifact = artifactRepository.findById(id);
        if (optionalArtifact.isPresent()) {
            return Artifact.toModel(optionalArtifact.get());
        } else {
            throw new ArtifactNotFoundException();
        }
    }

    public Artifact updateArtifact(UUID id, Artifact newArtifact) throws ArtifactNotFoundException {
        Optional<ArtifactEntity> optionalArtifact = artifactRepository.findById(id);
        if (optionalArtifact.isPresent()) {
            ArtifactEntity artifactEntity = optionalArtifact.get();
            artifactEntity.rememberThisVersion();
            artifactEntity.setUserId(newArtifact.getUserId());
            artifactEntity.setCategory(newArtifact.getCategory());
            artifactEntity.setDescription(newArtifact.getDescription());
            artifactRepository.save(artifactEntity);
            return Artifact.toModel(artifactEntity);
        } else {
            throw new ArtifactNotFoundException();
        }
    }

    public Artifact deleteArtifact(UUID id) throws ArtifactNotFoundException {
        Optional<ArtifactEntity> optionalArtifact = artifactRepository.findById(id);
        if (optionalArtifact.isPresent()) {
            artifactRepository.deleteById(id);
            return Artifact.toModel(optionalArtifact.get());
        } else {
            throw new ArtifactNotFoundException();
        }
    }

    public List<ArtifactEntity> getAllArtifactsByCategory(String category) {
        return artifactRepository.findAllByCategory(category);
    }

    public List<ArtifactEntity> getAllArtifactsByUserId(String userId) {
        return artifactRepository.findAllByUserId(userId);
    }

    public List<ArtifactEntity> getAllArtifactsByDescription(String description) {
        return artifactRepository.findAllByDescription(description);
    }

    public List<ArtifactEntity> getAllArtifactsByCommentContent(String content) {
        return artifactRepository.findAllByCommentsContent(content).stream().distinct().collect(Collectors.toList());
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
