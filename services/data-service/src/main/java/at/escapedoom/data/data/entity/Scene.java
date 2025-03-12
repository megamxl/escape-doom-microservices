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
@Table(name = "scene", uniqueConstraints = {
        @UniqueConstraint(name = "uniqueScenePerLevel", columnNames = { LEVEL_ID, SCENE_SEQUENCE }) })
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = SCENE_ID)
    private UUID sceneId;

    @Column(name = SCENE_SEQUENCE)
    private Integer sceneSequence;

    private String name;

    @Column(name = "background_image_uri")
    private String backgroundImageUri;

    @ManyToOne
    @JoinColumn(name = LEVEL_ID, insertable = false, updatable = false)
    private Level level;

    @Column(name = LEVEL_ID)
    private UUID levelId;

    @OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Node> nodes;

    @Override
    public String toString() {
        return "Scene{" + "sceneId=" + sceneId + ", sceneSequence=" + sceneSequence + ", name='" + name + '\''
                + ", backgroundImageURI='" + backgroundImageUri + '\'' + ", level=" + level + ", nodes=" + nodes + '}';
    }
}
