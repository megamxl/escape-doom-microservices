package at.escapedoom.data.data.entity;

import at.escapedoom.data.rest.model.CodingLanguage;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static at.escapedoom.data.data.entity.Constants.LEVEL_ID;

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
    @Column(name = "riddle_id")
    private UUID riddleId;

    @Column(name = "expected_output")
    private String expectedOutput;

    @Enumerated(EnumType.STRING)
    private CodingLanguage language;

    @Column(name = "function_signature")
    private String functionSignature;

    private String input;

    @Column(name = "variable_name")
    private String variableName;

    @Column(name = LEVEL_ID, nullable = false)
    private UUID levelId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = LEVEL_ID, referencedColumnName = LEVEL_ID, insertable = false, updatable = false)
    private Level level;

    @Override
    public String toString() {
        return "DBRiddle{" + "escapeRoomRiddleId=" + riddleId + ", expectedOutput='" + expectedOutput + '\''
                + ", language=" + language + ", functionSignature='" + functionSignature + '\'' + ", input='" + input
                + '\'' + ", variableName='" + variableName + '\'' + '}';
    }
}
