package at.escapedoom.data.data.entity.riddle;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("STRING_COMPARE")
@Data
@NoArgsConstructor
@AllArgsConstructor // Generates constructor with all fields for this specific class
@EqualsAndHashCode(callSuper = true) // Include superclass fields in equals/hashCode
public class StringCompareRiddle extends Riddle {

    private String image;

    public StringCompareRiddle(String image, List<TestCaseEntity> testCases) {
        super();
        setType(RiddleType.STRING_COMPARE);
        setImage(image);
        setTestCases(testCases); // Set testCases via the superclass setter
    }
}