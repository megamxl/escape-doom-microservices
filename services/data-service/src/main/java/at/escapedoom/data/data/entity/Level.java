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
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "level_id")
    private UUID levelId;

    // @OneToMany(mappedBy = "escapeRoomLevel", cascade = { CascadeType.PERSIST,
    // CascadeType.MERGE }, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "riddle_id", referencedColumnName = "escapeRoomRiddleId")
    private Riddle riddle;

//    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
//    @JoinTable(name = "escape_room_level_scene", joinColumns = @JoinColumn(name = "escape_room_level_id"), inverseJoinColumns = {
//            @JoinColumn(name = "scene_id", referencedColumnName = "sceneId"),
//            @JoinColumn(name = "scene_sequence", referencedColumnName = "sceneSequence")
//    })
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private List<Scene> scenes;

    private Integer levelSequence;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Template.class)
    @JoinColumn(name = "template_id", insertable = false, updatable = false)
    private Template template;

    @Column(name = "template_id")
    private UUID templateId;

    @Override
    public String toString() {
        return "EscapeRoomLevel{" + "escapeRoomLevelId=" + levelId + ", levelSequence=" + levelSequence
                + ", version=" + version + ", template="
                + (template != null ? template.getEscapeRoomTemplateID() : null) + '}';
    }
}
