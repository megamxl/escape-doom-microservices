package at.escapedoom.data.data.entity;

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
@ToString
@Table(name = "escape_room_template")
public class EscapeRoomTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomTemplateID;

    private Long userId;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EscapeRoomLevel> escapeRoomLevels;

    private String description;

    private String name;

    @Version
    private Integer version;

}
