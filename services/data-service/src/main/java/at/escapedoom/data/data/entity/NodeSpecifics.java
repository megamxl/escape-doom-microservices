package at.escapedoom.data.data.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NodeSpecifics {

    // region ConsoleNode
    private String return_description;

    private String constraints;

    private String example;
    // endregion

    // region ZoomNode
    private String linked_scene_id;
    private String parent_scene_id;
    // endregion

    // region DetailNode
    // endregion


    @Override
    public String toString() {
        return "NodeSpecifics{" +
                "return_description='" + return_description + '\'' +
                ", constraints='" + constraints + '\'' +
                ", example='" + example + '\'' +
                ", linked_scene_id='" + linked_scene_id + '\'' +
                ", parent_scene_id='" + parent_scene_id + '\'' +
                '}';
    }
}
