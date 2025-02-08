package at.escapedoom.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Entity
public class Scene {

    @Id
    private UUID escapeRoomSequenceId;

    private Integer sceneSequence;
    private String name;
    private URI backgroundImageURI;

    @OneToMany
    private List<Node> nodes;

}
