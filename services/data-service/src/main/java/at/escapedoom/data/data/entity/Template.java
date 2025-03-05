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
@Table(name = "template")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "template_id")
    private UUID escapeRoomTemplateID;

    @Column(nullable = false)
    private UUID userId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "template_id")
    private List<Level> escapeRoomLevels;

    private String description;

    private String name;

    @Override
    public String toString() {
        return "EscapeRoomTemplate{" + "escapeRoomTemplateID=" + escapeRoomTemplateID + ", userId=" + userId
                + ", escapeRoomLevels=" + escapeRoomLevels + ", description='" + description + '\'' + ", name='" + name
                + '\'' + '}';
    }
}
