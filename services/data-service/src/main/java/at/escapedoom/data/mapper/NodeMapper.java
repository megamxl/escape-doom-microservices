package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.*;
import at.escapedoom.data.rest.model.NodeCreationRequest;
import at.escapedoom.data.rest.model.NodeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface NodeMapper {

    @Mapping(source = "nodeId", target = "nodeId", qualifiedByName = "uuidToString")
    @Mapping(source = "nodeSpecifics", target = "nodeSpecifics", qualifiedByName = "mapNodeSpecToString")
    NodeDTO toDTO(Node node);

    @Mapping(source = "nodeId", target = "nodeId", qualifiedByName = "stringToUUID")
    @Mapping(target = "nodeSpecifics", source = "nodeSpecifics", ignore = true)
    Node toEntity(NodeDTO nodeDTO);

    @Mapping(source = "sceneId", target = "sceneId", qualifiedByName = "stringToUUID")
    @Mapping(target = "nodeSpecifics", source = "nodeSpecifics", ignore = true)
    Node toEntity(NodeCreationRequest creationRequest);

    @AfterMapping
    default void handleNodeSpecifics(@MappingTarget Node node, NodeCreationRequest creationRequest) {
        Class<? extends NodeSpecifics> clazz;
        switch (creationRequest.getNodeSpecifics().getNodeType()) {
        case CONSOLE -> clazz = ConsoleNodeSpecifics.class;
        case DETAIL -> clazz = DetailsNodeSpecifics.class;
        case ZOOM -> clazz = ZoomNodeSpecifics.class;
        default -> throw new IllegalArgumentException(
                "Unsupported node type: " + creationRequest.getNodeSpecifics().getNodeType());
        }
        try {
            node.setNodeSpecifics(clazz.cast(creationRequest.getNodeSpecifics()));
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Unable to convert node to JSON", e);
        }
    }

    @AfterMapping
    default void handleNodeSpecifics(@MappingTarget Node node, NodeDTO dto) {
        Class<? extends NodeSpecifics> clazz;
        switch (dto.getNodeSpecifics().getNodeType()) {
        case CONSOLE -> clazz = ConsoleNodeSpecifics.class;
        case DETAIL -> clazz = DetailsNodeSpecifics.class;
        case ZOOM -> clazz = ZoomNodeSpecifics.class;
        default -> throw new IllegalArgumentException("Unsupported node type: " + dto.getNodeSpecifics().getNodeType());
        }
        try {
            node.setNodeSpecifics(clazz.cast(dto.getNodeSpecifics()));
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Unable to convert node to JSON", e);
        }
    }

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUUID")
    default UUID stringToUUID(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }

    @Named("mapNodeSpecToString")
    default Object mapNodeSpecifics(NodeSpecifics nodeSpecifics) {
        if (nodeSpecifics == null) {
            return null;
        }
        try {
            return new ObjectMapper().writeValueAsString(nodeSpecifics);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    default List<NodeDTO> toDTOList(List<Node> nodes) {
        return nodes != null ? nodes.stream().map(this::toDTO).toList() : null;
    }

    default List<Node> toEntityList(List<NodeDTO> nodeDTOs) {
        return nodeDTOs != null ? nodeDTOs.stream().map(this::toEntity).toList() : null;
    }
}
