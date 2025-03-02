package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private UUID sceneId;

    private Integer sceneSequence;
    private String name;
    private String backgroundImageURI;

    @ManyToOne
    @JoinColumn(name = "escape_room_level_id", nullable = false)
    private EscapeRoomLevel escapeRoomLevel;

    @Column(name = "escape_room_sequence_id", nullable = false, unique = true)
    private UUID escapeRoomSequenceId;

    @OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Node> nodes;
}
