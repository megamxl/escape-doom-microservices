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
@ToString
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
}
