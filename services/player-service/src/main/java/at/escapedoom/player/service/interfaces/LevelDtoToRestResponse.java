package at.escapedoom.player.service.interfaces;

import at.escapedoom.spring.communication.data.model.LevelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LevelDtoToRestResponse {

    LevelDtoToRestResponse levelMapper = Mappers.getMapper(LevelDtoToRestResponse.class);

//    @Mapping(target = "scenes", ignore = true)
    @Mapping(target = "riddle", ignore = true)
    at.escapedoom.player.rest.model.LevelDTO toRest(LevelDTO levelDTO);
}
