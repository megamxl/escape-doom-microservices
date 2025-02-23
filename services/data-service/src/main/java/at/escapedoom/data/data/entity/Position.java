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
public class Position {

    private Double topPercentage;

    private Double leftPercentage;
}
