package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

import static at.escapedoom.data.data.entity.Constants.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "level", uniqueConstraints = {
        @UniqueConstraint(name = "uniqueTemplate", columnNames = { TEMPLATE_ID, LEVEL_SEQUENCE }) })
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = LEVEL_ID)
    private UUID levelId;

    @Column(name = LEVEL_SEQUENCE)
    private Integer levelSequence;

    // @OneToMany(mappedBy = "escapeRoomLevel", cascade = { CascadeType.PERSIST,
    // CascadeType.MERGE }, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = RIDDLE_ID)
    private Riddle riddle;

    // @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    // @JoinTable(name = "escape_room_level_scene", joinColumns = @JoinColumn(name = "escape_room_level_id"),
    // inverseJoinColumns = {
    // @JoinColumn(name = "scene_id", referencedColumnName = "sceneId"),
    // @JoinColumn(name = "scene_sequence", referencedColumnName = "sceneSequence")
    // })
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = LEVEL_ID)
    private List<Scene> scenes;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Template.class)
    @JoinColumn(name = TEMPLATE_ID, insertable = false, updatable = false)
    private Template template;

    @Column(name = TEMPLATE_ID)
    private UUID templateId;

    @Override
    public String toString() {
        return "Level{" + "levelId=" + levelId + ", levelSequence=" + levelSequence + ", riddle=" + riddle + ", scenes="
                + scenes + ", version=" + version + ", template=" + template + ", templateId=" + templateId + '}';
    }
}
