package at.escapedoom.spring.redis.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@RedisHash("session-view")
public class SessionView implements Serializable {

    @Id
    private Long roomPin;

    private EscapeRoomState roomState;

    private UUID escapeRoomTemplateId;
}
