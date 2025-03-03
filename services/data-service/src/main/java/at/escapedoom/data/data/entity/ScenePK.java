package at.escapedoom.data.data.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ScenePK implements Serializable {

    private UUID sceneId;
    private Integer sceneSequence;
}
