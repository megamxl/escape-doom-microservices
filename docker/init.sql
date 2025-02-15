DO $$
    BEGIN
        CREATE USER dataApi WITH PASSWORD 'escapedoom';
        CREATE USER sessionApi WITH PASSWORD 'escapedoom';
        CREATE USER leaderboardApi WITH PASSWORD 'escapedoom';
        CREATE USER playerApi WITH PASSWORD 'escapedoom';
    EXCEPTION WHEN duplicate_object THEN RAISE NOTICE '%, skipping', SQLERRM USING ERRCODE = SQLSTATE;
    END
$$;

CREATE SCHEMA IF NOT EXISTS data_schema AUTHORIZATION admin;
CREATE SCHEMA IF NOT EXISTS session_schema AUTHORIZATION admin;
CREATE SCHEMA IF NOT EXISTS leaderboard_schema AUTHORIZATION admin;
CREATE SCHEMA IF NOT EXISTS player_schema AUTHORIZATION admin;

GRANT ALL PRIVILEGES ON SCHEMA data_schema TO dataApi;
GRANT ALL PRIVILEGES ON SCHEMA session_schema TO sessionApi;
GRANT ALL PRIVILEGES ON SCHEMA leaderboard_schema TO leaderboardApi;
GRANT ALL PRIVILEGES ON SCHEMA player_schema TO playerApi;

-- Grant access to admin and api_user
GRANT USAGE ON SCHEMA data_schema TO dataApi;
GRANT USAGE ON SCHEMA session_schema TO sessionApi;
GRANT USAGE ON SCHEMA leaderboard_schema TO leaderboardApi;
GRANT USAGE ON SCHEMA player_schema TO playerApi;

GRANT CREATE, USAGE ON SCHEMA data_schema TO dataApi;
GRANT CREATE, USAGE ON SCHEMA session_schema TO sessionApi;
GRANT CREATE, USAGE ON SCHEMA leaderboard_schema TO leaderboardApi;
GRANT CREATE, USAGE ON SCHEMA player_schema TO playerApi;

-- Grant full privileges on all tables inside the schemas
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA data_schema TO dataApi;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA session_schema TO sessionApi;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA leaderboard_schema TO leaderboardApi;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA player_schema TO playerApi;

-- Ensure future tables inherit permissions
ALTER DEFAULT PRIVILEGES IN SCHEMA data_schema GRANT ALL ON TABLES TO dataApi;
ALTER DEFAULT PRIVILEGES IN SCHEMA session_schema GRANT ALL ON TABLES TO sessionApi;
ALTER DEFAULT PRIVILEGES IN SCHEMA leaderboard_schema GRANT ALL ON TABLES TO leaderboardApi;
ALTER DEFAULT PRIVILEGES IN SCHEMA player_schema GRANT ALL ON TABLES TO playerApi;