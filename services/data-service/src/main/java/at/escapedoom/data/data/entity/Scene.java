package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "scene")
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomSequenceId;

    private Integer sceneSequence;

    private String name;

    private URI backgroundImageURI;

    @OneToMany
    private List<Node> nodes;

    @Override
    public String toString() {
        return "Scene{" + "escapeRoomSequenceId=" + escapeRoomSequenceId + ", sceneSequence=" + sceneSequence
                + ", name='" + name + '\'' + ", backgroundImageURI=" + backgroundImageURI + ", nodes=lazyLoaded" + '}';
    }
}
