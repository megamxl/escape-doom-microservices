package at.escapedoom.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class NodeInfo {

    private String description;
    private String title;
    private String imageURI;

}
