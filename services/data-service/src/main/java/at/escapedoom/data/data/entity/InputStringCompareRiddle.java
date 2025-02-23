package at.escapedoom.data.data.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Generic")
public class InputStringCompareRiddle extends Riddle {

    private String inputString;

}
