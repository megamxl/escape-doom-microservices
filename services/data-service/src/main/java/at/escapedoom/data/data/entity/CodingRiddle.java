package at.escapedoom.data.data.entity;

import at.escapedoom.data.rest.model.CodingLanguage;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Coding")
public class CodingRiddle extends Riddle {

    private CodingLanguage language;

    private String functionSignature;

    private String input;

    private String variableName;
}
