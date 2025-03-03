package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
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

    // TODO: SceneSequence should be part of key
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sceneId;

    private Integer sceneSequence;
    private String name;
    private String backgroundImageURI;

    @ManyToOne
    @JoinColumn(name = "escape_room_level_id")
    private EscapeRoomLevel escapeRoomLevel;

    @Column(name = "escape_room_sequence_id", unique = true)
    private UUID escapeRoomSequenceId;

    @OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Node> nodes;

    @Override
    public String toString() {
        return "Scene{" + "escapeRoomSequenceId=" + escapeRoomSequenceId + ", sceneSequence=" + sceneSequence
                + ", name='" + name + '\'' + ", backgroundImageURI=" + backgroundImageURI + ", nodes=lazyLoaded" + '}';
    }
}
