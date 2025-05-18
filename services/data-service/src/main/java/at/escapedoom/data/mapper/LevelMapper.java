package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.rest.model.LevelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { NodeMapper.class })
public interface LevelMapper {

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "uuidToString")
    @Mapping(source = "templateId", target = "templateId", qualifiedByName = "uuidToString")
    @Mapping(source = "riddle", target = "riddle")
    LevelDTO toDTO(Level level);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "stringToUUID")
    @Mapping(source = "templateId", target = "templateId", qualifiedByName = "stringToUUID")
    @Mapping(source = "riddle", target = "riddle")
    Level toEntity(LevelDTO levelDTO);
}
