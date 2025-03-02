package at.escapedoom.data.data.entity;

import at.escapedoom.data.rest.model.CodingLanguage;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Riddle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomRiddleId;

    private String expectedOutput;

    @Enumerated(EnumType.STRING)
    private CodingLanguage language;

    private String functionSignature;

    private String input;

    private String variableName;

    @ManyToOne
    @JoinColumn(name = "escape_room_level_id")
    private EscapeRoomLevel escapeRoomLevel;

    @Override
    public String toString() {
        return "DBRiddle{" + "escapeRoomRiddleId=" + escapeRoomRiddleId + ", expectedOutput='" + expectedOutput + '\''
                + ", language=" + language + ", functionSignature='" + functionSignature + '\'' + ", input='" + input
                + '\'' + ", variableName='" + variableName + '\'' + '}';
    }
}
