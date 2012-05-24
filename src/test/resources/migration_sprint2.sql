CREATE TABLE property_services
(
    property_id Integer not null,
    services varchar(255),

    FOREIGN KEY(property_id) REFERENCES properties(id)
);

INSERT INTO property_services (SELECT id FROM properties WHERE barbecue = TRUE);
UPDATE property_services SET services = 'BARBECUE' WHERE services is null;
INSERT INTO property_services (SELECT id FROM properties WHERE paddle = TRUE);
UPDATE property_services SET services = 'PADDLE' WHERE services is null;
INSERT INTO property_services (SELECT id FROM properties WHERE cable = TRUE);
UPDATE property_services SET services = 'CABLE' WHERE services is null;
INSERT INTO property_services (SELECT id FROM properties WHERE phone = TRUE);
UPDATE property_services SET services = 'PHONE' WHERE services is null;
INSERT INTO property_services (SELECT id FROM properties WHERE lounge = TRUE);
UPDATE property_services SET services = 'LOUNGE' WHERE services is null;
INSERT INTO property_services (SELECT id FROM properties WHERE pool = TRUE);
UPDATE property_services SET services = 'POOL' WHERE services is null;

alter table properties add reserved boolean;
alter table properties add visited Integer;

UPDATE properties set reserved = false;
UPDATE properties set visited = 0;

alter table users alter column id set not null;
alter table users alter column name set not null;
alter table users alter column surname set not null;
alter table users alter column email set not null;
alter table users alter column phone set not null;
alter table users alter column username set not null;
alter table users alter column password set not null;

alter table users add realEstateName varchar(50);
alter table users add logoExtension varchar(10);

alter table properties alter column id set not null;
alter table properties alter column propertyType set not null;
alter table properties alter column operationType set not null;
alter table properties alter column address set not null;
alter table properties alter column neighbourhood set not null;
alter table properties alter column price set not null;

alter table properties rename rooms to numRooms;

alter table properties alter column numRooms set not null;
alter table properties alter column indoorSpace set not null;
alter table properties alter column outdoorSpace set not null;
alter table properties alter column description set not null;
alter table properties alter column antiquity set not null;
alter table properties alter column published set not null;
alter table properties alter column reserved set not null;
alter table properties alter column visited set not null;

alter table properties drop column barbecue;
alter table properties drop column paddle;
alter table properties drop column cable;
alter table properties drop column phone;
alter table properties drop column lounge;
alter table properties drop column pool;

alter table properties rename userId to user_id;

alter table pictures alter column id set not null;
alter table pictures alter column extension set not null;
alter table pictures alter column name set not null;

alter table pictures rename propertyId to property_id;

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

GRANT ALL PRIVILEGES ON TABLE property_services to paw;

GRANT ALL PRIVILEGES ON TABLE rooms to paw;

GRANT ALL PRIVILEGES ON TABLE rooms_id_seq to paw;

GRANT ALL PRIVILEGES ON DATABASE paw1 to paw;

GRANT ALL PRIVILEGES ON TABLE users to paw;

GRANT ALL PRIVILEGES ON TABLE properties to paw;

GRANT ALL PRIVILEGES ON TABLE pictures to paw;

GRANT ALL PRIVILEGES ON TABLE pictures_id_seq to paw;

GRANT ALL PRIVILEGES ON TABLE properties_id_seq to paw;

GRANT ALL PRIVILEGES ON TABLE users_id_seq to paw;


