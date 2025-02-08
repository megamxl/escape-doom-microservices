package at.escapedoom.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Position {

    private Double top;

    private Double left;
}
