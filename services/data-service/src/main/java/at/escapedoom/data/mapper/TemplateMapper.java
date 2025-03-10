package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.rest.model.LevelDTO;
import at.escapedoom.data.rest.model.TemplateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = LevelMapper.class)
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    @Mapping(source = "templateId", target = "templateId", qualifiedByName = "templateUuidToString")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "templateUuidToString")
    @Mapping(source = "level", target = "levels", qualifiedByName = "levelListToDTOList")
    TemplateDTO toDTO(Template template);

    @Mapping(source = "templateId", target = "templateId", qualifiedByName = "templateStringToUUID")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "templateStringToUUID")
    @Mapping(source = "levels", target = "level", qualifiedByName = "dtoListToLevelList")
    Template toEntity(TemplateDTO templateDTO);

    @Named("templateUuidToString")
    static String templateUuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("templateStringToUUID")
    static UUID templateStringToUUID(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }

    @Named("levelListToDTOList")
    static List<LevelDTO> levelListToDTOList(List<at.escapedoom.data.data.entity.Level> levels) {
        return levels != null ? levels.stream().map(LevelMapper.INSTANCE::toDTO).toList() : null;
    }

    @Named("dtoListToLevelList")
    static List<at.escapedoom.data.data.entity.Level> dtoListToLevelList(List<LevelDTO> levelDTOs) {
        return levelDTOs != null ? levelDTOs.stream().map(LevelMapper.INSTANCE::toEntity).toList() : null;
    }
}
