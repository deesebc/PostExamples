	CREATE TABLESPACE BOOK_DATA DATAFILE 'tablespaceBOOK_DATA.dat' SIZE 100M AUTOEXTEND ON;
	
	CREATE USER BOOK identified by BOOK;
	ALTER USER BOOK quota unlimited on BOOK_DATA;
	
	GRANT create session TO BOOK;
	GRANT create table TO BOOK;
	GRANT create view TO BOOK;
	GRANT create any trigger TO BOOK;
	GRANT create any procedure TO BOOK;
	GRANT create sequence TO BOOK;
	GRANT create synonym TO BOOK;
	
	ALTER SESSION SET current_schema = BOOK;

--------------------------------------------------------
--  DDL for Table BOOK
--------------------------------------------------------



CREATE TABLE "BOOK"."BOOK" (
	"ID" NUMBER(6,0) NOT NULL, 
	"AUTHOR" VARCHAR2(255 BYTE), 
	"NAME" VARCHAR2(255 BYTE), 
	"RELEASE" DATE ,
	PRIMARY KEY (ID)
)
TABLESPACE "BOOK_DATA";

INSERT INTO Book(id, name, author, release) VALUES (1, 'Ender Game', 'Orson S.Card', TO_DATE('1985/01/15', 'yyyy/mm/dd'));
INSERT INTO Book(id, name, author, release) VALUES (2, 'Dune', 'Frank Herber', TO_DATE('1965/01/01', 'yyyy/mm/dd'));
INSERT INTO Book(id, name, author, release) VALUES (3, 'Brave New World', 'Aldous Huxley', TO_DATE('1956/06/01', 'yyyy/mm/dd'));