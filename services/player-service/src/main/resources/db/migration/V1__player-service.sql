CREATE TABLE IF NOT EXISTS user_progress (
                                             user_identifier UUID PRIMARY KEY,
                                             user_name VARCHAR(255),
    room_pin BIGINT,
    current_escape_room_level BIGINT,
    last_riddle_solved_at TIMESTAMP,
    current_points BIGINT,
    template_id UUID
    );

CREATE TABLE IF NOT EXISTS solution_attempt (
                                                solution_attempt_id BIGSERIAL PRIMARY KEY,
                                                player_uuid UUID UNIQUE,
                                                language VARCHAR(255),
    current_escape_room_level BIGINT,
    code_submition VARCHAR(1024),
    output VARCHAR(1024),
    status VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS result (
                                      id BIGSERIAL PRIMARY KEY,
                                      escape_room_level BIGINT,
                                      input VARCHAR(10000),
    solved_level_at TIMESTAMP,
    awarded_points DOUBLE PRECISION,
    user_identifier UUID REFERENCES user_progress(user_identifier)
    );
