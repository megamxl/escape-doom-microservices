package at.escapedoom.data.entity;

import enums.NodeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                ", position=" + position +
                ", nodeType=" + nodeType +
                ", nodeInfo=" + nodeInfo +
                '}';
    }
}
