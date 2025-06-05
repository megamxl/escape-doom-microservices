package at.escapedoom.data.data.entity.riddle;

import at.escapedoom.data.rest.model.CodingLanguage;
import at.escapedoom.data.utils.CodingLanguageMapConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

import static at.escapedoom.data.data.entity.riddle.RiddleType.CODING;

@Entity
@DiscriminatorValue("CODING")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CodingRiddleEntity extends Riddle {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "code", columnDefinition = "jsonb")
    Map<CodingLanguage, String> code;

    public CodingRiddleEntity(List<TestCaseEntity> testCases, Map<CodingLanguage, String> code) {
        super();
        setType(CODING);
        setCode(code);
        setTestCases(testCases); // Set testCases via the superclass setter
    }

    public void setCode(Map<CodingLanguage, String> code) {
        // TODO figure out if i want to validate here
        this.code = code;
    }
}