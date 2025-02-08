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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID nodeId;

    @Embedded
    private Position position;

    @Enumerated(EnumType.STRING)
    private NodeType nodeType;

    @Embedded
    private NodeInfo nodeInfo;



}
