/*
CREATE TABLE region (
    id SERIAL PRIMARY KEY,
    num_region VARCHAR(255) NOT NULL UNIQUE,
    nom_region VARCHAR(255) NOT NULL
);

CREATE TABLE zone (
    -- ...
    FOREIGN KEY (region_id) REFERENCES region(id)
);

CREATE TABLE secteur (
    -- ...
    FOREIGN KEY (zone_id) REFERENCES zone(id)
);

CREATE TABLE poste (
    -- ...
    FOREIGN KEY (secteur_id) REFERENCES secteur(id)
    -- ... autres clés étrangères
);

CREATE TABLE moniteur (
    id SERIAL PRIMARY KEY,
    matr_moniteur VARCHAR(255) NOT NULL UNIQUE,
    ann_entrer_mon YEAR,
    code_secteur VARCHAR(255) NOT NULL,
    heure TIME,
    FOREIGN KEY (id) REFERENCES personnel(id)
);

CREATE TABLE inspecteur (
    id SERIAL PRIMARY KEY,
    matr_inspect VARCHAR(255) NOT NULL UNIQUE,
    etat_civil VARCHAR(255) NOT NULL,
    date_entrer_vozama YEAR,
    -- ... autres colonnes
    FOREIGN KEY (id) REFERENCES personnel(id)
);

CREATE TABLE eleve (
    id SERIAL PRIMARY KEY,
    matr_eleve VARCHAR(50) NOT NULL UNIQUE,
    nom_eleve VARCHAR(100) NOT NULL,
    -- ... other columns
    poste_id INT REFERENCES poste(id) NOT NULL
);

CREATE TABLE matiere (
    id SERIAL PRIMARY KEY,
    nom_matiere VARCHAR(100) NOT NULL,
    coefficient INT NOT NULL
);

CREATE TABLE examen (
    id SERIAL PRIMARY KEY,
    date_examen DATE NOT NULL,
    trimestre VARCHAR(20) NOT NULL
);

CREATE TABLE eleve_matiere_examen (
    eleve_id INT REFERENCES eleve(id),
    matiere_id INT REFERENCES matiere(id),
    examen_id INT REFERENCES examen(id),
    PRIMARY KEY (eleve_id, matiere_id, examen_id)
);

 */