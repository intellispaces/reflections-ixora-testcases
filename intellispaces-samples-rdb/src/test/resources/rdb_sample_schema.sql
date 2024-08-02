DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS payment;

CREATE TABLE person
(
    id int              NOT NULL,
    name VARCHAR(100)   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE payment
(
    id int              NOT NULL,
    person_id int       NOT NULL,
    amount int          NOT NULL,
    PRIMARY KEY (id)
);
