package at.escapedoom.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "riddle_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Riddle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomRiddleId;

    private String expectedOutput;

}
