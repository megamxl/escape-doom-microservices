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
@Table(name = "escape_room_level")
public class EscapeRoomLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomLevelId;

    // @OneToMany(mappedBy = "escapeRoomLevel", cascade = { CascadeType.PERSIST,
    // CascadeType.MERGE }, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "riddle_id", referencedColumnName = "escapeRoomRiddleId")
    private Riddle riddle;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "escape_room_level_scene", joinColumns = @JoinColumn(name = "escape_room_level_id"), inverseJoinColumns = @JoinColumn(name = "scene_id"))
    private List<Scene> scenes;

    private Integer levelSequence;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escape_room_template_id", nullable = true)
    private EscapeRoomTemplate template;

    @Override
    public String toString() {
        return "EscapeRoomLevel{" + "escapeRoomLevelId=" + escapeRoomLevelId + ", levelSequence=" + levelSequence
                + ", version=" + version + ", template="
                + (template != null ? template.getEscapeRoomTemplateID() : null) + '}';
    }
}
