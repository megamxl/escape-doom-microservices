package at.escapedoom.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class Scene {

    @Id
    private UUID escapeRoomSequenceId;
    private Integer SceneSequence;
    private String name;
    private String backgroundImageURI;

    @OneToMany
    private List<Node> nodes;

}
