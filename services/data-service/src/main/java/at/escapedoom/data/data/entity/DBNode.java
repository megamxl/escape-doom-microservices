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
public class DBNode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID nodeId;

    @Embedded
    private DBPosition position;

    @Embedded
    private DBNodeInfo nodeInfo;

    @Enumerated(EnumType.STRING)
    private NodeType nodeType;

    @Override
    public String toString() {
        return "Node{" + "nodeId=" + nodeId + ", position=" + position + ", nodeType=" + nodeType + ", nodeInfo="
                + nodeInfo + '}';
    }
}
