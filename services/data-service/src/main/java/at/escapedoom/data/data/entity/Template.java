package at.escapedoom.data.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

import static at.escapedoom.data.data.entity.Constants.TEMPLATE_ID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "template")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = TEMPLATE_ID)
    private UUID templateId;

    @Column(nullable = false)
    private UUID userId;

    @OneToMany(mappedBy = "template", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Level> level;

    private String description;

    private String name;

    @Override
    public String toString() {
        return "Template{" + "templateId=" + templateId + ", userId=" + userId + '}';
    }
}
