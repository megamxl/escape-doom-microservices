package at.escapedoom.data.data.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ZoomNodeSpecifics extends NodeSpecifics {

    private String linked_scene_id;
    private String parent_scene_id;

}
