-- MESSAGES
CREATE TABLE IF NOT EXISTS messages
(
    id      VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY ,
    text    VARCHAR NOT NULL
);
-- PERSON
CREATE TABLE IF NOT EXISTS persons
(
    id          VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY ,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL
);

-- CUSTOMERS
CREATE TABLE IF NOT EXISTS customers
(
    id          VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY ,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL
);
