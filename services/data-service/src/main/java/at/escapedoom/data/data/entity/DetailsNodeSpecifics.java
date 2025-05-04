package at.escapedoom.data.data.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DetailsNodeSpecifics extends NodeSpecifics {

    private String image_uri;

}
