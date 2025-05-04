CREATE TABLE IF NOT EXISTS template (
                                        template_id UUID PRIMARY KEY,
                                        user_id UUID NOT NULL,
                                        description VARCHAR(255),
    name VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS level (
                                     level_id UUID PRIMARY KEY,
                                     template_id UUID,
                                     level_sequence INT,
                                     name VARCHAR(255),
                                     version INT,
                                     CONSTRAINT fk_level_template FOREIGN KEY (template_id) REFERENCES template(template_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS riddle (
                                      riddle_id UUID PRIMARY KEY,
                                      level_id UUID UNIQUE NOT NULL,
                                      expected_output VARCHAR(255),
    language VARCHAR(255),
    function_signature VARCHAR(255),
    input VARCHAR(255),
    variable_name VARCHAR(255),
    CONSTRAINT fk_riddle_level FOREIGN KEY (level_id) REFERENCES level(level_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS scene (
                                     scene_id UUID PRIMARY KEY,
                                     level_id UUID,
                                     scene_sequence INT,
                                     background_image_uri VARCHAR(255),
    name VARCHAR(255),
    CONSTRAINT fk_scene_level FOREIGN KEY (level_id) REFERENCES level(level_id) ON DELETE CASCADE,
    CONSTRAINT unique_level_scene_sequence UNIQUE (level_id, scene_sequence)
    );

CREATE TABLE IF NOT EXISTS node (
                                    node_id UUID PRIMARY KEY,
                                    scene_id UUID,
                                    node_type VARCHAR(255),
    description VARCHAR(255),
    imageuri VARCHAR(255),
    title VARCHAR(255),
    left_percentage DOUBLE,
    top_percentage DOUBLE,
    CONSTRAINT fk_node_scene FOREIGN KEY (scene_id) REFERENCES scene(scene_id) ON DELETE CASCADE
    );
