-- address ─────────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS address (
    id     BIGSERIAL    PRIMARY KEY,
    street VARCHAR(100) NOT NULL,
    city   VARCHAR(45)  NOT NULL
);

-- student ─────────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS student (
    id         BIGSERIAL   PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50),
    email      VARCHAR(100),
    address_id BIGINT      REFERENCES address (id)
);

CREATE INDEX IF NOT EXISTS idx_student_address ON student (address_id);

-- subject ─────────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS subject (
    id             BIGSERIAL        PRIMARY KEY,
    subject_name   VARCHAR(45),
    marks_obtained DOUBLE PRECISION,
    student_id     BIGINT           REFERENCES student (id)
);

CREATE INDEX IF NOT EXISTS idx_subject_student ON subject (student_id);

-- author ──────────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS author (
    id    UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

-- post ────────────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS post (
    id          UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    category    VARCHAR(100),
    author_id   UUID         REFERENCES author (id)
);

CREATE INDEX IF NOT EXISTS idx_post_author ON post (author_id);
