create table IF NOT EXISTS book (id integer not null auto_increment, author varchar(255), name varchar(255), release_date date, primary key (id)) engine=InnoDB;

--INSERT INTO Book(id, name, author, release) VALUES (1, 'Ender Game', 'Orson S.Card', TO_DATE('1985/01/15', 'yyyy/mm/dd'));
--INSERT INTO Book(id, name, author, release) VALUES (2, 'Dune', 'Frank Herber', TO_DATE('1965/01/01', 'yyyy/mm/dd'));
--INSERT INTO Book(id, name, author, release) VALUES (3, 'Brave New World', 'Aldous Huxley', TO_DATE('1956/06/01', 'yyyy/mm/dd'));

INSERT INTO book(id, name, author, release_date) VALUES (1, 'Ender Game', 'Orson S.Card', STR_TO_DATE('15/01/1985', '%d/%m/%Y'));
INSERT INTO book(id, name, author, release_date) VALUES (2, 'Dune', 'Frank Herber', STR_TO_DATE('15/01/1965', '%d/%m/%Y'));
INSERT INTO book(id, name, author, release_date) VALUES (3, 'The starts my destinatio', 'Alfred Bester', STR_TO_DATE('01/06/1956', '%d/%m/%Y'));