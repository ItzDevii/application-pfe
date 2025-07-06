-- Members table
CREATE TABLE IF NOT EXISTS members (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    join_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL
);

-- Activities table
CREATE TABLE IF NOT EXISTS activities (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    activity_date DATE NOT NULL
);

-- Audit Logs table
CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGSERIAL PRIMARY KEY,
    action VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    entity VARCHAR(255) NOT NULL,
    entity_id BIGINT NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

-- Cotisations table
CREATE TABLE IF NOT EXISTS cotisations (
    id BIGSERIAL PRIMARY KEY,
    payment_date DATE NOT NULL,
    amount NUMERIC(10,2) NOT NULL,
    member_id BIGINT NOT NULL,
    CONSTRAINT fk_cotisation_member FOREIGN KEY (member_id) REFERENCES members(id)
);

-- Documents table
CREATE TABLE IF NOT EXISTS documents (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL
);

-- Participations table
CREATE TABLE IF NOT EXISTS participations (
    id BIGSERIAL PRIMARY KEY,
    member_id BIGINT NOT NULL,
    activity_id BIGINT NOT NULL,
    CONSTRAINT fk_participation_member FOREIGN KEY (member_id) REFERENCES members(id),
    CONSTRAINT fk_participation_activity FOREIGN KEY (activity_id) REFERENCES activities(id)
);

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
