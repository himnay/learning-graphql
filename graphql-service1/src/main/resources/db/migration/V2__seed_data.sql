-- addresses ───────────────────────────────────────────────────────────────────
INSERT INTO address (id, street, city) VALUES
    (1, 'Happy Street', 'Delhi'),
    (2, '2nd Street',   'Mumbai'),
    (3, '3rd Street',   'Delhi'),
    (4, 'Any Street',   'Mumbai');

-- students ────────────────────────────────────────────────────────────────────
INSERT INTO student (id, first_name, last_name, email, address_id) VALUES
    (1, 'John',   'Smith',  'john@gmail.com',   1),
    (2, 'Virat',  'Dave',   'virat@gmail.com',  2),
    (3, 'Steve',  'Martin', 'steve@gmail.com',  3),
    (4, 'Sachin', 'Kumar',  'sachin@gmail.com', 4);

-- subjects ────────────────────────────────────────────────────────────────────
INSERT INTO subject (id, subject_name, marks_obtained, student_id) VALUES
    (1,  'Java',    80, 1),
    (2,  'MySql',   70, 1),
    (3,  'Java',    80, 2),
    (4,  'MySql',   70, 2),
    (5,  'MongoDB', 70, 2),
    (6,  'MySql',   70, 3),
    (7,  'MongoDB', 70, 3),
    (8,  'Java',    60, 4),
    (9,  'MongoDB', 50, 4);

-- reset sequences so new inserts don't conflict with seeded IDs
SELECT setval('address_id_seq', (SELECT MAX(id) FROM address));
SELECT setval('student_id_seq', (SELECT MAX(id) FROM student));
SELECT setval('subject_id_seq', (SELECT MAX(id) FROM subject));
