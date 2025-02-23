package at.escapedoom.data.data.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Embeddable
public class NodeInfo {

    private String description;

    private String title;

    private String imageURI;
}
