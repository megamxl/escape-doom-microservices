
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
@ToString
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
    @JoinColumn(name = "scene_id", nullable = false)
    private Scene scene;
}
