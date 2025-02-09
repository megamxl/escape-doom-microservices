package at.escapedoom.data.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Position {

    private Double topPercentage;

    private Double leftPercentage;

    @Override
    public String toString() {
        return "Position{" +
                "top=" + topPercentage +
                ", left=" + leftPercentage +
                '}';
    }
}
