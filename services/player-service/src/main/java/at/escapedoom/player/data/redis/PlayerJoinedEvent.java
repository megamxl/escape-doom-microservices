package at.escapedoom.player.data.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerJoinedEvent {

    String roomPin;
    String userName;
    String userIdentifier;
}
