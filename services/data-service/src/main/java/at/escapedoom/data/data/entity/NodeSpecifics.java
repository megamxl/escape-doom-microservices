package at.escapedoom.data.data.entity;

import at.escapedoom.data.rest.model.NodeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@RequiredArgsConstructor
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "node_type")
@JsonSubTypes({ @JsonSubTypes.Type(value = ConsoleNodeSpecifics.class, name = "CONSOLE"),
        @JsonSubTypes.Type(value = DetailsNodeSpecifics.class, name = "DETAIL"),
        @JsonSubTypes.Type(value = ZoomNodeSpecifics.class, name = "ZOOM") })
public abstract class NodeSpecifics {

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private NodeType nodeType;

}
