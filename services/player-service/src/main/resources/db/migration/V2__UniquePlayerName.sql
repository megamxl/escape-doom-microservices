ALTER TABLE
    user_progress
    ADD CONSTRAINT unique_username_per_room UNIQUE (room_pin, user_name);