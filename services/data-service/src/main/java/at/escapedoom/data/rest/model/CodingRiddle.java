package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CodingRiddle
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class CodingRiddle implements RiddlePostRequest {

  private String escapeRoomRiddleId;

  /**
   * The type of the riddle
   */
  public enum TypeEnum {
    INPUT_STRING_COMPARE_RIDDLE("InputStringCompareRiddle"),
    
    CODING_RIDDLE("CodingRiddle");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  private String expectedOutput;

  private String language;

  private String functionSignature;

  private String input;

  private String variableName;

  public CodingRiddle escapeRoomRiddleId(String escapeRoomRiddleId) {
    this.escapeRoomRiddleId = escapeRoomRiddleId;
    return this;
  }

  /**
   * The unique ID of the riddle
   * @return escapeRoomRiddleId
   */
  
  @Schema(name = "escape_room_riddle_id", example = "5830daed-cb7f-47dd-8248-5dee9bf0aa3d", description = "The unique ID of the riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("escape_room_riddle_id")
  public String getEscapeRoomRiddleId() {
    return escapeRoomRiddleId;
  }

  public void setEscapeRoomRiddleId(String escapeRoomRiddleId) {
    this.escapeRoomRiddleId = escapeRoomRiddleId;
  }

  public CodingRiddle type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of the riddle
   * @return type
   */
  
  @Schema(name = "type", description = "The type of the riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public CodingRiddle expectedOutput(String expectedOutput) {
    this.expectedOutput = expectedOutput;
    return this;
  }

  /**
   * The expected output of the function
   * @return expectedOutput
   */
  
  @Schema(name = "expected_output", example = "5", description = "The expected output of the function", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expected_output")
  public String getExpectedOutput() {
    return expectedOutput;
  }

  public void setExpectedOutput(String expectedOutput) {
    this.expectedOutput = expectedOutput;
  }

  public CodingRiddle language(String language) {
    this.language = language;
    return this;
  }

  /**
   * The programming language used in the riddle
   * @return language
   */
  
  @Schema(name = "language", example = "Java", description = "The programming language used in the riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("language")
  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public CodingRiddle functionSignature(String functionSignature) {
    this.functionSignature = functionSignature;
    return this;
  }

  /**
   * The function signature
   * @return functionSignature
   */
  
  @Schema(name = "function_signature", example = "public static int sum(int a, int b)", description = "The function signature", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("function_signature")
  public String getFunctionSignature() {
    return functionSignature;
  }

  public void setFunctionSignature(String functionSignature) {
    this.functionSignature = functionSignature;
  }

  public CodingRiddle input(String input) {
    this.input = input;
    return this;
  }

  /**
   * The input values for the function
   * @return input
   */
  
  @Schema(name = "input", example = "2, 3", description = "The input values for the function", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("input")
  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public CodingRiddle variableName(String variableName) {
    this.variableName = variableName;
    return this;
  }

  /**
   * The name of the variable to compare
   * @return variableName
   */
  
  @Schema(name = "variable_name", example = "result", description = "The name of the variable to compare", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("variable_name")
  public String getVariableName() {
    return variableName;
  }

  public void setVariableName(String variableName) {
    this.variableName = variableName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CodingRiddle codingRiddle = (CodingRiddle) o;
    return Objects.equals(this.escapeRoomRiddleId, codingRiddle.escapeRoomRiddleId) &&
        Objects.equals(this.type, codingRiddle.type) &&
        Objects.equals(this.expectedOutput, codingRiddle.expectedOutput) &&
        Objects.equals(this.language, codingRiddle.language) &&
        Objects.equals(this.functionSignature, codingRiddle.functionSignature) &&
        Objects.equals(this.input, codingRiddle.input) &&
        Objects.equals(this.variableName, codingRiddle.variableName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(escapeRoomRiddleId, type, expectedOutput, language, functionSignature, input, variableName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CodingRiddle {\n");
    sb.append("    escapeRoomRiddleId: ").append(toIndentedString(escapeRoomRiddleId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    expectedOutput: ").append(toIndentedString(expectedOutput)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    functionSignature: ").append(toIndentedString(functionSignature)).append("\n");
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("    variableName: ").append(toIndentedString(variableName)).append("\n");
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

