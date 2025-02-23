package at.escapedoom.data.data.entity;

import enums.NodeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
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
}
