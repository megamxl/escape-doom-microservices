package at.escapedoom.data.data.entity;

import at.escapedoom.data.rest.model.CodingLanguage;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static at.escapedoom.data.data.entity.Constants.LEVEL_ID;
import static at.escapedoom.data.data.entity.Constants.RIDDLE_ID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "riddle")
public class Riddle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = RIDDLE_ID)
    private UUID riddleId;

    private String expectedOutput;

    @Enumerated(EnumType.STRING)
    private CodingLanguage language;

    private String functionSignature;

    private String input;

    private String variableName;

    @OneToOne(mappedBy = "riddle")
    private Level level;

    @Column(name = LEVEL_ID, nullable = false)
    private UUID levelId;

    @Override
    public String toString() {
        return "DBRiddle{" + "escapeRoomRiddleId=" + riddleId + ", expectedOutput='" + expectedOutput + '\''
                + ", language=" + language + ", functionSignature='" + functionSignature + '\'' + ", input='" + input
                + '\'' + ", variableName='" + variableName + '\'' + '}';
    }
}
