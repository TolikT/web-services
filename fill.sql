CREATE TABLE "persons" (
 id bigserial NOT NULL,
 name character varying(200),
 surname character varying(200),
 age integer,
 CONSTRAINT "Persons_pkey" PRIMARY KEY (id)
);

INSERT INTO persons(name, surname, age) values ('Петр', 'Петров', 25);
INSERT INTO persons(name, surname, age) values ('Владимир', 'Иванов', 26);
INSERT INTO persons(name, surname, age) values ('Иван', 'Иванов', 27);
INSERT INTO persons(name, surname, age) values ('Иммануил', 'Кант', 28);
INSERT INTO persons(name, surname, age) values ('Джордж', 'Клуни', 29);
INSERT INTO persons(name, surname, age) values ('Билл', 'Рубцов', 30);
INSERT INTO persons(name, surname, age) values ('Марк', 'Марков', 31);
INSERT INTO persons(name, surname, age) values ('Галина', 'Матвеева', 32);
INSERT INTO persons(name, surname, age) values ('Святослав', 'Павлов', 33);
INSERT INTO persons(name, surname, age) values ('Ольга', 'Бергольц', 34);
INSERT INTO persons(name, surname, age) values ('Лев', 'Рабинович', 35);
