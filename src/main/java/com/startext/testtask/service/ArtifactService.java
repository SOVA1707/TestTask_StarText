package com.startext.testtask.service;

import com.startext.testtask.entity.ArtifactEntity;
import com.startext.testtask.exception.ArtifactAlreadyExistException;
import com.startext.testtask.exception.ArtifactNotFoundException;
import com.startext.testtask.model.Artifact;
import com.startext.testtask.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ArtifactService {

    @Autowired
    private ArtifactRepository artifactRepository;

    public ArtifactEntity createArtifact(ArtifactEntity artifact) throws ArtifactAlreadyExistException {
        if (!artifactRepository.existsById(artifact.getId())) {
            return artifactRepository.save(artifact);
        } else {
            throw new ArtifactAlreadyExistException();
        }
    }

    public Artifact getArtifact(UUID id) throws ArtifactNotFoundException {
        //If findBySuuid is executed first, then findById will be executed.
        //Otherwise, findById will not find anything.
        //Check uuid id annotation.
        artifactRepository.findBySuuid(id.toString());
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
}
