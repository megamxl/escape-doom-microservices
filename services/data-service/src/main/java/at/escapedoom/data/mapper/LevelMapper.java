package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.NodeSpecifics;
import at.escapedoom.data.rest.model.LevelDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

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
