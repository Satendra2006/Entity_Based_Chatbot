-- USERS TABLE
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT UNIQUE,
    password TEXT,
    role TEXT
);

-- INTENTS TABLE
CREATE TABLE IF NOT EXISTS intents (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE
);

-- ENTITIES TABLE
CREATE TABLE IF NOT EXISTS entities (
    id SERIAL PRIMARY KEY,
    name TEXT,
    intent_id INT REFERENCES intents(id) ON DELETE CASCADE
);

-- KNOWLEDGE BASE TABLE
CREATE TABLE IF NOT EXISTS knowledge_base (
    id SERIAL PRIMARY KEY,
    intent_id INT REFERENCES intents(id) ON DELETE CASCADE,
    entity TEXT,
    attribute TEXT,
    value TEXT
);

-- CHAT LOGS TABLE
CREATE TABLE IF NOT EXISTS chat_logs (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE SET NULL,
    query TEXT,
    intent TEXT,
    entity TEXT,
    response TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);