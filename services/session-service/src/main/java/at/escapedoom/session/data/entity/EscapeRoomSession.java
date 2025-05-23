package at.escapedoom.session.data.entity;

import at.escapedoom.session.rest.model.EscapeRoomState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "escape_room_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EscapeRoomSession {
    @Id
    private UUID sessionId;
    private UUID templateId;

    @Column(unique = true, nullable = false)
    private Long roomPin;

    private UUID userId;
    private Long playTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private EscapeRoomState state;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "escape_room_tags", joinColumns = @JoinColumn(name = "escape_room_session_id"))
    @Column(name = "tag")
    private List<String> tags;

}
