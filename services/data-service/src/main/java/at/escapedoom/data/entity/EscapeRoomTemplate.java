package at.escapedoom.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "escape_room_template")
public class EscapeRoomTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomTemplateID;

    private Long userId;

    @OneToMany
    private List<EscapeRoomLevel> escapeRoomLevels;

    private String description;

    private String name;

    @Override
    public String toString() {
        return "EscapeRoomTemplate{" + "escapeRoomTemplateID=" + escapeRoomTemplateID + ", userId=" + userId
                + ", escapeRoomLevels=" + escapeRoomLevels + ", description='" + description + '\'' + ", name='" + name
                + '\'' + '}';
    }
}
