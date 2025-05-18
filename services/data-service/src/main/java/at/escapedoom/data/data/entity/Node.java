package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

import static at.escapedoom.data.data.entity.Constants.NODE_ID;
import static at.escapedoom.data.data.entity.Constants.SCENE_ID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "node")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = NODE_ID)
    private UUID nodeId;

    @Embedded
    private Position position;

    private String description;

    private String title;

    @ManyToOne
    @JoinColumn(name = SCENE_ID, insertable = false, updatable = false)
    private Scene scene;

    @Column(name = SCENE_ID)
    private UUID sceneId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "node_specifics", columnDefinition = "jsonb")
    private NodeSpecifics nodeSpecifics;

    @Override
    public String toString() {
        return "Node{" + "nodeId=" + nodeId + ", position=" + position + ", description='" + description + '\''
                + ", title='" + title + '\'' + ", scene=" + scene + ", sceneId=" + sceneId + ", nodeSpecifics="
                + nodeSpecifics + '}';
    }
}
