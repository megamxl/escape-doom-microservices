package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "scene")
@IdClass(ScenePK.class)
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sceneId;

    @Id
    private Integer sceneSequence;

    private String name;
    private String backgroundImageURI;

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "level_id")
    private Level escapeRoomLevel;

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
