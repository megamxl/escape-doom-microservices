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
@Table(name = "escape_room_level")
public class EscapeRoomLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID escapeRoomLevelId;

    @OneToMany
    private List<DBScene> scenes;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "escapeRoomRiddleId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DBRiddle riddle;

    private Integer levelSequence;

    @Override
    public String toString() {
        return "EscapeRoomLevel{" + "escapeRoomLevelId=" + escapeRoomLevelId + ", scenes=lazyLoading" + ", riddle="
                + riddle + ", levelSequence=" + levelSequence + '}';
    }
}
