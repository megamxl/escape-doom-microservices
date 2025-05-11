CREATE TABLE escape_room_session (
                                     session_id UUID PRIMARY KEY,
                                     template_id UUID,
                                     room_pin BIGINT NOT NULL UNIQUE,
                                     user_id UUID,
                                     play_time BIGINT,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     start_time TIMESTAMP,
                                     end_time TIMESTAMP,
                                     state VARCHAR(255)
);

CREATE TABLE escape_room_tags (
                                  escape_room_session_id UUID NOT NULL,
                                  tag VARCHAR(255),
                                  CONSTRAINT fk_escape_room_tags_session FOREIGN KEY (escape_room_session_id) REFERENCES escape_room_session(session_id)
);