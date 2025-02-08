package at.escapedoom.data.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "escape_room_level")
public class EscapeRoomLevel {

    @Id
    private UUID escapeRoomLevelId;

    @OneToMany
    private List<Scene> scenes;

    @OneToOne
    private Riddle riddle;

    private Integer levelSequence;
}
