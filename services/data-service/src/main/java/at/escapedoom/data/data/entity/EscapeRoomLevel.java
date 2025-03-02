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
@ToString
@Table(name = "escape_room_level")
public class EscapeRoomLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomLevelId;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "escape_room_level_scene", joinColumns = @JoinColumn(name = "escape_room_level_id"), inverseJoinColumns = @JoinColumn(name = "scene_id"))
    private List<Scene> scenes;

    @OneToMany(mappedBy = "escapeRoomLevel", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<Riddle> riddles;

    private Integer levelSequence;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escape_room_template_id", nullable = true)
    private EscapeRoomTemplate template;
}
