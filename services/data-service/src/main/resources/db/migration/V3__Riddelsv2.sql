DROP TABLE IF EXISTS riddle;

CREATE TABLE riddle (
                        riddle_id         UUID PRIMARY KEY,
                        level_id          UUID UNIQUE NOT NULL,
                        riddle_type       VARCHAR(200) NOT NULL,
                        test_cases        JSONB,
                        code              JSONB,
                        image             TEXT,
                        CONSTRAINT fk_riddle_level FOREIGN KEY (level_id) REFERENCES level (level_id) ON DELETE CASCADE
);