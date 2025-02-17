package at.escapedoom.data.data.entity;

import enums.CodingLanguage;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
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
