package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Node;
import at.escapedoom.data.rest.model.NodeCreationRequest;
import at.escapedoom.data.rest.model.NodeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface NodeMapper {

    NodeMapper INSTANCE = Mappers.getMapper(NodeMapper.class);

    @Mapping(source = "nodeId", target = "nodeId", qualifiedByName = "uuidToString")
    NodeDTO toDTO(Node node);

    @Mapping(source = "nodeId", target = "nodeId", qualifiedByName = "stringToUUID")
    Node toEntity(NodeDTO nodeDTO);

    @Mapping(source = "sceneId", target = "sceneId", qualifiedByName = "stringToUUID")
    Node toEntity(NodeCreationRequest creationRequest);

    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUUID")
    static UUID stringToUUID(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }

    default List<NodeDTO> toDTOList(List<Node> nodes) {
        return nodes != null ? nodes.stream().map(this::toDTO).toList() : null;
    }

    default List<Node> toEntityList(List<NodeDTO> nodeDTOs) {
        return nodeDTOs != null ? nodeDTOs.stream().map(this::toEntity).toList() : null;
    }
}
