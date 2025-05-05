package at.escapedoom.player.service.interfaces;

import at.escapedoom.spring.communication.data.model.LevelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LevelDtoToRestResponse {

    LevelDtoToRestResponse levelMapper = Mappers.getMapper(LevelDtoToRestResponse.class);

    // @Mapping(source = "scenes.nodes.position", target = "scenes.nodes.position")
    at.escapedoom.player.rest.model.LevelDTO toRest(LevelDTO levelDTO);
}
