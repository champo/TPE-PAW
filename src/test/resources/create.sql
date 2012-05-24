CREATE TABLE users
(
    id serial not null,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    phone varchar(20) not null,
    username varchar(50) not null,
    password char(64) not null,
    realEstateName varchar(50),
    logoExtension varchar(10),

    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX indexUsersId on users(id);

CREATE UNIQUE INDEX indexUsersUsername on users(username);

CREATE TABLE properties
(
    id serial not null,
    propertyType Integer not null,
    operationType Integer not null,
    address varchar(50) not null,
    neighbourhood varchar(50) not null,
    price double precision not null,
    numRooms Integer not null,
    indoorSpace double precision not null,
    outDoorSpace double precision not null,
    description varchar(1000) not null,
    antiquity Integer not null,
    user_id Integer,
    published boolean not null,
    reserved boolean not null,
    visited Integer not null,

    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE UNIQUE INDEX indexPropertiesId on properties(id);

CREATE INDEX indexPropertiesUserId on properties(user_id);

CREATE INDEX indexPropertiesPrice on properties(price);

CREATE TABLE pictures
(
    id serial not null,
    name varchar(50) not null,
    property_id Integer not null,
    extension varchar(10) not null,

    PRIMARY KEY(id),
    FOREIGN KEY(property_id) REFERENCES properties(id)
);

CREATE INDEX indexPicturesId on pictures(id);

CREATE INDEX indexPicturesPropertyId on pictures(property_id);

CREATE TABLE property_services
(
    property_id Integer not null,
    services varchar(255),

    FOREIGN KEY(property_id) REFERENCES properties(id)
);

CREATE INDEX indexPropertyServices on property_services(property_id);

CREATE TABLE rooms
(
    id serial not null,
    length double precision not null,
    name varchar(50) not null,
    width double precision not null,
    property_id Integer,

    PRIMARY KEY(id),
    FOREIGN KEY(property_id) REFERENCES properties(id)
);

CREATE INDEX indexRoomsId on rooms(id);

CREATE INDEX indexRoomsPropertyId on rooms(property_id);

GRANT ALL PRIVILEGES ON DATABASE paw1 to paw;

GRANT ALL PRIVILEGES ON TABLE users to paw;

GRANT ALL PRIVILEGES ON TABLE properties to paw;

GRANT ALL PRIVILEGES ON TABLE pictures to paw;

GRANT ALL PRIVILEGES ON TABLE pictures_id_seq to paw;

GRANT ALL PRIVILEGES ON TABLE properties_id_seq to paw;

GRANT ALL PRIVILEGES ON TABLE users_id_seq to paw;

GRANT ALL PRIVILEGES ON TABLE property_services to paw;

GRANT ALL PRIVILEGES ON TABLE rooms to paw;

GRANT ALL PRIVILEGES ON TABLE rooms_id_seq to paw;

