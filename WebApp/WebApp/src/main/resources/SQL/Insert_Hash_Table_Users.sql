CREATE EXTENSION pgcrypto;

SELECT * FROM pg_available_extensions WHERE name = 'pgcrypto';

INSERT INTO Users (login, password)
VALUES ('user1', crypt('password123', gen_salt('bf')));

INSERT INTO Users (login, password)
VALUES ('user2', crypt('password1', gen_salt('bf')));

INSERT INTO Users (login, password)
VALUES ('user', crypt('password', gen_salt('bf')));

SELECT * FROM Users
WHERE login = 'user1' AND password = crypt('password123', password);

SELECT * FROM Users
WHERE login = 'user2' AND password = crypt('password1', password);

SELECT * FROM Users
WHERE login = 'user' AND password = crypt('password', password);


--sha-256 hashed

INSERT INTO Users (login, password)
VALUES ('user1200', encode(digest('password1234', 'sha256'), 'hex'));

INSERT INTO Users (login, password)
VALUES ('user2100', encode(digest('password124', 'sha256'), 'hex'));

INSERT INTO Users (login, password)
VALUES ('user309', encode(digest('password34', 'sha256'), 'hex'));

