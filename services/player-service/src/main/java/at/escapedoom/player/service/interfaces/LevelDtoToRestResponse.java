package at.escapedoom.player.service.interfaces;

import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.spring.communication.data.model.LevelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LevelDtoToRestResponse {

    LevelDtoToRestResponse levelMapper = Mappers.getMapper(LevelDtoToRestResponse.class);

    EscapeRoomLevel toRest(LevelDTO levelDTO);
}
