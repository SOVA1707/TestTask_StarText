package com.startext.testtask.controller;

import com.startext.testtask.exception.ArtifactAlreadyExistException;
import com.startext.testtask.exception.ArtifactNotFoundException;
import com.startext.testtask.model.Artifact;
import com.startext.testtask.model.ArtifactCreationDTO;
import com.startext.testtask.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/artifacts")
public class ArtifactController {

    @Autowired
    private ArtifactService artifactService;

    @PutMapping
    public ResponseEntity createArtifact(@RequestBody ArtifactCreationDTO artifact) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(artifactService.createArtifact(ArtifactCreationDTO.fromModel(artifact)).getId());
        } catch (ArtifactAlreadyExistException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response = ResponseEntity.badRequest().body("Error on create artifact.");
        }
        return response;
    }

    @GetMapping
    public ResponseEntity getArtifact(@RequestParam UUID id) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(artifactService.getArtifact(id));
        } catch (ArtifactNotFoundException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error on get artifact.");
        }
        return response;
    }

    @PostMapping
    public ResponseEntity updateArtifact(@RequestParam UUID id, @RequestBody Artifact artifact) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(artifactService.updateArtifact(id, artifact));
        } catch (ArtifactNotFoundException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error on update artifact.");
        }
        return response;
    }

    @DeleteMapping
    public ResponseEntity deleteArtifact(@RequestParam UUID id) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(artifactService.deleteArtifact(id));
        } catch (ArtifactNotFoundException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error on delete artifact.");
        }
        return response;
    }
}
