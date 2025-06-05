package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * The solution to submit
 */
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.Setter

@Schema(name = "EscapeRoomSolutionSubmition", description = "The solution to submit")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomSolutionSubmition {

    private @Nullable String solution;

    /**
     * The language of the solution
     */
    public enum LanguageEnum {
        JAVA("JAVA"),

        PYTHON("PYTHON"),

        JS("JS");

        private String value;

        LanguageEnum(String value) {
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
        public static LanguageEnum fromValue(String value) {
            for (LanguageEnum b : LanguageEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private @Nullable LanguageEnum language;

    public EscapeRoomSolutionSubmition solution(String solution) {
        this.solution = solution;
        return this;
    }

    /**
     * The solution to submit
     *
     * @return solution
     */

    @Schema(name = "solution", example = "public static void main(String[] args) { public static int riddle1() { return -1; } }", description = "The solution to submit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("solution")
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public EscapeRoomSolutionSubmition language(LanguageEnum language) {
        this.language = language;
        return this;
    }

    /**
     * The language of the solution
     *
     * @return language
     */

    @Schema(name = "language", example = "JAVA", description = "The language of the solution", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("language")
    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EscapeRoomSolutionSubmition escapeRoomSolutionSubmition = (EscapeRoomSolutionSubmition) o;
        return Objects.equals(this.solution, escapeRoomSolutionSubmition.solution)
                && Objects.equals(this.language, escapeRoomSolutionSubmition.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solution, language);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomSolutionSubmition {\n");
        sb.append("    solution: ").append(toIndentedString(solution)).append("\n");
        sb.append("    language: ").append(toIndentedString(language)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
