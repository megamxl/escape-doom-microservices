package at.escapedoom.player.ws;

import at.escapedoom.spring.redis.data.models.EscapeRoomState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class StateChange {
    EscapeRoomState state;
}
