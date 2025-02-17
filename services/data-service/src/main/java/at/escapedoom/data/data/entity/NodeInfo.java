package at.escapedoom.data.data.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class NodeInfo {

    private String description;

    private String title;

    private String imageURI;

    @Override
    public String toString() {
        return "NodeInfo{" + "description='" + description + '\'' + ", title='" + title + '\'' + ", imageURI='"
                + imageURI + '\'' + '}';
    }
}
