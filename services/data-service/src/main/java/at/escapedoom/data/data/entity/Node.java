package at.escapedoom.data.data.entity;

import at.escapedoom.data.rest.model.NodeType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
    private UUID nodeId;

    @Embedded
    private Position position;

    @Embedded
    private NodeInfo nodeInfo;

    @Enumerated(EnumType.STRING)
    private NodeType nodeType;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "scene_id", referencedColumnName = "sceneId"),
            @JoinColumn(name = "scene_sequence", referencedColumnName = "sceneSequence") })
    private Scene scene;

    @Override
    public String toString() {
        return "Node{" + "nodeId=" + nodeId + ", nodeType=" + nodeType + ", nodeInfo=" + nodeInfo + '}';
    }
}
