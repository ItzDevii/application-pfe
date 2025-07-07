CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE members (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    join_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_member_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE contributions (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(10, 2) NOT NULL CHECK (amount > 0),
    date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    member_id BIGINT NOT NULL,
    CONSTRAINT fk_contribution_member FOREIGN KEY (member_id) REFERENCES members(id)
);

CREATE TABLE activities (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE participations (
    id BIGSERIAL PRIMARY KEY,
    member_id BIGINT NOT NULL,
    activity_id BIGINT NOT NULL,
    CONSTRAINT fk_participation_member FOREIGN KEY (member_id) REFERENCES members(id),
    CONSTRAINT fk_participation_activity FOREIGN KEY (activity_id) REFERENCES activities(id)
);

CREATE TABLE documents (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL,
    path TEXT NOT NULL,
    uploaded_by BIGINT,
    CONSTRAINT fk_document_user FOREIGN KEY (uploaded_by) REFERENCES users(id)
);

CREATE TABLE audit_logs (
    id BIGSERIAL PRIMARY KEY,
    action VARCHAR(255) NOT NULL,
    target_entity VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    user_id BIGINT,
    CONSTRAINT fk_audit_user FOREIGN KEY (user_id) REFERENCES users(id)
);
