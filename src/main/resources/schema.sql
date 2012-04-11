CREATE TABLE users
(
    id serial,
    name varchar(50),
    surname varchar(50),
    email varchar(50),
    phone varchar(20),
    username varchar(50),
    password char(64),

    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX indexUsersId on users(id);

CREATE UNIQUE INDEX indexUsersUsername on users(username);

CREATE TABLE properties
(
    id serial,
    propertyType smallint,
    operationType smallint,
    address varchar(50),
    neighbourhood varchar(50),
    price double precision,
    rooms smallint,
    indoorSpace double precision,
    outDoorSpace double precision,
    description varchar(1000),
    antiquity smallint,
    cable boolean,
    phone boolean,
    pool boolean,
    lounge boolean,
    paddle boolean,
    barbecue boolean,
    published boolean,
    userId Integer,

    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES users(id)
);

CREATE UNIQUE INDEX indexPropertiesId on properties(id);

CREATE INDEX indexPropertiesUserId on properties(userId);

CREATE INDEX indexPropertiesPrice on properties(price);

CREATE TABLE pictures
(
    id serial,
    name varchar(50),
    propertyId Integer,
    extension varchar(10),
    
    PRIMARY KEY(id),
    FOREIGN KEY(propertyId) REFERENCES properties(id)
);

CREATE INDEX indexPicturesId on pictures(id);

CREATE INDEX indexPicturesPropertyId on pictures(propertyId);

GRANT ALL PRIVILEGES ON DATABASE paw1 to paw1;

GRANT ALL PRIVILEGES ON TABLE users to paw1;

GRANT ALL PRIVILEGES ON TABLE properties to paw1;

GRANT ALL PRIVILEGES ON TABLE pictures to paw1;

GRANT ALL PRIVILEGES ON TABLE pictures_id_seq to paw1;

GRANT ALL PRIVILEGES ON TABLE properties_id_seq to paw1;

GRANT ALL PRIVILEGES ON TABLE users_id_seq to paw1;


