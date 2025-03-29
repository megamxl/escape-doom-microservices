package at.escapedoom.player.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PlayerNamesMessage {
    List<String> message;
}
