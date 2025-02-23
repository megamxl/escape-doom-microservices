package at.escapedoom.data.data.entity;

import at.escapedoom.data.rest.model.CodingLanguage;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DBRiddle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomRiddleId;

    private String expectedOutput;

    private CodingLanguage language;

    private String functionSignature;

    private String input;

    private String variableName;
}
