package at.escapedoom.data.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "riddle_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Riddle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID escapeRoomRiddleId;

}
