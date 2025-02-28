package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "riddle_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Riddle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomRiddleId;

    private String expectedOutput;

}
