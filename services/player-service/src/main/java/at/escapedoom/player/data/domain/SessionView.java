package at.escapedoom.player.data.domain;

import at.escapedoom.player.rest.model.EscapeRoomState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SessionView {

    private Long roomPin;

    private EscapeRoomState roomState;

    private UUID escapeRoomTemplateId;
}
