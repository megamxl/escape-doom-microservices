package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.CreateBadRequestDTO;
import at.escapedoom.data.rest.model.CreateInternalServerErrorDTO;
import at.escapedoom.data.rest.model.CreateNotFoundDTO;
import at.escapedoom.data.rest.model.NodeCreationRequest;
import at.escapedoom.data.rest.model.NodeDTO;
import at.escapedoom.data.rest.model.NodeDeletionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link NodeApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public interface NodeApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /nodes : Creates node for level Creates a new node for a specific level
     *
     * @param nodeCreationRequest
     *            Lectors ID + Name and Description for a Node (required)
     *
     * @return Node created successfully (status code 201)
     *
     * @see NodeApi#createNode
     */
    default ResponseEntity<NodeDTO> createNode(NodeCreationRequest nodeCreationRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /nodes/{node-id} : Delete a node Delete a node by its ID
     *
     * @param nodeId
     *            The unique ID of the node (required)
     *
     * @return Node deleted successfully (status code 200) or Not Found (status code 404) or Internal Server Error
     *         (status code 500)
     *
     * @see NodeApi#deleteNode
     */
    default ResponseEntity<NodeDeletionResponseDTO> deleteNode(String nodeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Node deleted successfully\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /nodes : Get all nodes Retrieve all nodes from a Lector
     *
     * @return A list of nodes (status code 200) or Internal Server Error (status code 500)
     *
     * @see NodeApi#getAllNodes
     */
    default ResponseEntity<List<NodeDTO>> getAllNodes() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /nodes/{node-id} : Get details of a node Retrieve details of a specific node by its ID
     *
     * @param nodeId
     *            The unique ID of the node (required)
     *
     * @return Node details (status code 200) or Not Found (status code 404) or Internal Server Error (status code 500)
     *
     * @see NodeApi#getNode
     */
    default ResponseEntity<NodeDTO> getNode(String nodeId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /nodes/{node-id} : Override a node Override the details of a node
     *
     * @param nodeId
     *            The unique ID of the node (required)
     * @param nodeDTO
     *            The overridden details of the node (required)
     *
     * @return Node updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status code
     *         404) or Internal Server Error (status code 500)
     *
     * @see NodeApi#updateNode
     */
    default ResponseEntity<NodeDTO> updateNode(String nodeId, NodeDTO nodeDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Invalid data provided\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
