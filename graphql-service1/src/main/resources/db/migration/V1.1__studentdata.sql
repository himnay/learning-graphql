INSERT INTO address(id, street, city) VALUES (1,'Happy Street','Delhi');
INSERT INTO address(id, street, city) VALUES (2,'2nd Street','Mumbai');
INSERT INTO address(id, street, city) VALUES (3,'3rd Street','Delhi');
INSERT INTO address(id, street, city) VALUES (4,'any street','Mumbai');

INSERT INTO student(id, first_name, last_name, email, address_id) VALUES (1,'John','Smith','john@gmail.com',1);
INSERT INTO student(id, first_name, last_name, email, address_id) VALUES (2,'Virat','Dave','virat@gmail.com',2);
INSERT INTO student(id, first_name, last_name, email, address_id) VALUES (3,'Steve','Martin','steve@gmail.com',3);
INSERT INTO student(id, first_name, last_name, email, address_id) VALUES (4,'Sachin','Kumar','sachin@gmail.com',4);

INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (1,'Java',80,1);
INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (2,'MySQL',70,1);
INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (3,'Java',80,2);
INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (4,'MySQL',70,2);
INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (5,'MongoDB',70,2);
INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (6,'MySQL',70,3);
INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (7,'MongoDB',70,3);
INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (8,'Java',60,4);
INSERT INTO subject(id, subject_name, marks_obtained, student_id) VALUES (9,'MongoDB',50,4);