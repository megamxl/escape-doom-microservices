package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "escape_room_level")
public class EscapeRoomLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomLevelId;

    @OneToMany
    private List<Scene> scenes;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "escapeRoomRiddleId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Riddle riddle;

    private Integer levelSequence;

    @ManyToOne
    @JoinColumn(name = "escape_room_template_id", nullable = false)
    private EscapeRoomTemplate template;
}
