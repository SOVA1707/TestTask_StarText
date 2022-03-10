package com.startext.testtask.model;

import com.startext.testtask.entity.ArtifactEntity;

public class ArtifactCreationDTO {
    private String category;
    private String description;

    public ArtifactCreationDTO() {
    }

    public static ArtifactEntity fromModel(ArtifactCreationDTO model) {
        ArtifactEntity artifact = new ArtifactEntity();
        artifact.setCategory(model.getCategory());
        artifact.setDescription(model.getDescription());
        return artifact;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
