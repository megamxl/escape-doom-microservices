package at.escapedoom.data.data.entity.riddle;

import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.utils.TestCaseListConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static at.escapedoom.data.data.entity.Constants.LEVEL_ID;

@Getter
@Setter

@Entity
@Table(name = "riddle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "riddle_type", discriminatorType = DiscriminatorType.STRING) // Matches your 'type' property
public abstract class Riddle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "riddle_id")
    private UUID riddleId;

    @Column(name = "riddle_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private RiddleType type;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "test_cases", columnDefinition = "jsonb")
    private List<TestCaseEntity> testCases = new ArrayList<>(); // Initialize to avoid NPE

    @Column(name = LEVEL_ID, nullable = false)
    private UUID levelId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = LEVEL_ID, referencedColumnName = LEVEL_ID, insertable = false, updatable = false)
    private Level level;

    // Lombok's @Data will handle getters/setters.
    // Ensure the type is set by subclasses
    protected void setType(RiddleType type) {
        this.type = type;
    }
}
