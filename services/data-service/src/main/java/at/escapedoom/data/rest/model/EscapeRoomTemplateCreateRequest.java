package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The escape-room template, base for an EscapeRoom Game
 */

@Schema(name = "EscapeRoomTemplateCreateRequest", description = "The escape-room template, base for an EscapeRoom Game")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class EscapeRoomTemplateCreateRequest {

  private String name;

  private String description;

  public EscapeRoomTemplateCreateRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the Template
   * @return name
   */
  
  @Schema(name = "name", example = "SDE24", description = "Name of the Template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EscapeRoomTemplateCreateRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the Template
   * @return description
   */
  
  @Schema(name = "description", example = "Cäsar´s Rätsel", description = "Description of the Template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EscapeRoomTemplateCreateRequest escapeRoomTemplateCreateRequest = (EscapeRoomTemplateCreateRequest) o;
    return Objects.equals(this.name, escapeRoomTemplateCreateRequest.name) &&
        Objects.equals(this.description, escapeRoomTemplateCreateRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EscapeRoomTemplateCreateRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

