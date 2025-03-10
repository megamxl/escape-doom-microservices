package at.escapedoom.data.data.entity;

import at.escapedoom.data.rest.model.NodeType;
import jakarta.persistence.*;
import lombok.*;

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

    @Embedded
    private NodeInfo nodeInfo;

    @Enumerated(EnumType.STRING)
    private NodeType nodeType;

    @ManyToOne
    @JoinColumn(name = SCENE_ID, insertable = false, updatable = false)
    private Scene scene;

    @Column(name = SCENE_ID)
    private UUID sceneId;

    @Override
    public String toString() {
        return "Node{" + "nodeId=" + nodeId + ", nodeType=" + nodeType + ", nodeInfo=" + nodeInfo + '}';
    }
}
