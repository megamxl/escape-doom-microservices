package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

import static at.escapedoom.data.data.entity.Constants.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "level", uniqueConstraints = {
        @UniqueConstraint(name = "uniqueTemplate", columnNames = { TEMPLATE_ID, LEVEL_SEQUENCE }) })
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = LEVEL_ID)
    private UUID levelId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = LEVEL_SEQUENCE)
    private Integer levelSequence;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = RIDDLE_ID)
    private Riddle riddle;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = LEVEL_ID)
    private List<Scene> scenes;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Template.class)
    @JoinColumn(name = TEMPLATE_ID, insertable = false, updatable = false)
    private Template template;

    @Column(name = TEMPLATE_ID)
    private UUID templateId;

    @Override
    public String toString() {
        return "Level{" + "levelId=" + levelId + ", levelSequence=" + levelSequence + '}';
    }
}
