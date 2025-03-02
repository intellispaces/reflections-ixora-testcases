CREATE SCHEMA IF NOT EXISTS books;

SET SCHEMA books;

DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS book_order;

CREATE TABLE book
(
    id      int             NOT NULL,
    title   VARCHAR(100)    NOT NULL,
    author  VARCHAR(100)    NOT NULL,
    genre   VARCHAR(100)    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book_order
(
    id      int             NOT NULL,
    book_id int             NOT NULL,
    price   int             NOT NULL,
    PRIMARY KEY (id)
);
