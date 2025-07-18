-- USER TABLE
CREATE TABLE utilisateur (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- MEMBER TABLE
CREATE TABLE membre (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    date_adhesion DATE NOT NULL,
    statut VARCHAR(20) NOT NULL
);

-- COTISATION TABLE
CREATE TABLE cotisation (
    id SERIAL PRIMARY KEY,
    amount NUMERIC(10,2) NOT NULL,
    date_paiement DATE NOT NULL,
    id_membre INTEGER NOT NULL REFERENCES membre(id)
);

-- ACTIVITY TABLE
CREATE TABLE activite (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    date_evenement DATE NOT NULL,
    description VARCHAR(500)
);

-- PARTICIPATION TABLE
CREATE TABLE participation (
    id SERIAL PRIMARY KEY,
    id_membre INTEGER NOT NULL REFERENCES membre(id),
    id_activite INTEGER NOT NULL REFERENCES activite(id),
    date_inscription DATE NOT NULL
);

-- DOCUMENT TABLE
CREATE TABLE document (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    url VARCHAR(500) NOT NULL,
    id_membre INTEGER NOT NULL REFERENCES membre(id)
);

-- AUDIT LOG TABLE
CREATE TABLE journal (
    id SERIAL PRIMARY KEY,
    action VARCHAR(255) NOT NULL,
    timestamp DATE NOT NULL,
    id_utilisateur INTEGER NOT NULL REFERENCES utilisateur(id)
);

-- Add CHECK constraints for enums
ALTER TABLE utilisateur
  ADD CONSTRAINT chk_role CHECK (role IN ('ADMIN','USER'));

ALTER TABLE membre
  ADD CONSTRAINT chk_statut CHECK (statut IN ('ACTIVE','INACTIVE','SUSPENDED'));
