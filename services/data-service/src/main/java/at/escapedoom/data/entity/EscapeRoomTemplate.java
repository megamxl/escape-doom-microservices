package at.escapedoom.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "escape_room_template")
public class EscapeRoomTemplate {

    @Id
    private UUID escapeRoomTemplateID;

    private Long userId;

    @OneToMany
    private List<EscapeRoomLevel> escapeRoomLevels;

    private String description;

    private String name;
}
