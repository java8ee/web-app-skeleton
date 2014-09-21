INSERT INTO Person (name, surname) VALUES ('Username', 'Usersurname');
INSERT INTO Person (name, surname) VALUES ('Name', 'Surname');
INSERT INTO Person (name, surname) VALUES ('Ivan', 'Ivanov');
INSERT INTO Person (name, surname) VALUES ('Sergey', 'Sergeev');
INSERT INTO Person (name, surname) VALUES ('Anton', 'Antonov');
INSERT INTO Person (name, surname) VALUES ('Petr', 'Petrov');
INSERT INTO Person (name, surname) VALUES ('Semen', 'Sidorov');
INSERT INTO Person (name, surname) VALUES ('Mihail', 'Mihailov');

INSERT INTO User (username, password, person_id)
SELECT 'username', 'password', (SELECT id FROM Person WHERE surname ='Usersurname') FROM dual;

INSERT INTO User (username, password, person_id)
SELECT 'root', 'root', (SELECT id FROM Person WHERE surname ='Ivanov') FROM dual;