CREATE TABLE ad 
(
    weight Integer not null,
    url varchar(255) not null,
    clientname varchar(255) not null
);

CREATE TABLE property_states
(
    property_id Integer not null,
    date timestamp without time zone not null,
    newstate varchar(255) not null,
    oldstate varchar(255) not null,

    FOREIGN KEY(property_id) REFERENCES properties(id)
);

alter table properties add sold boolean;
alter table properties add currency varchar(255);
UPDATE properties SET sold = false;
UPDATE properties SET currency = '$';
alter table properties alter column sold set not null;
alter table properties alter column currency set not null;

DELETE FROM pictures;
alter table pictures add data oid;
alter table pictures alter column data set not null;

DELETE FROM rooms where property_id in (Select id from properties where user_id in(Select id from users where realestatename is not null));

DELETE FROM property_services where property_id in (Select id from properties where user_id in(Select id from users where realestatename is not null));
DELETE FROM properties where user_id in (Select id from users where realestatename is not null);
DELETE FROM users where realestatename is not null;
alter table users add photo oid;
alter table users add type varchar(255);
UPDATE users SET type = 'REGULAR';
alter table users alter column type set not null;

alter table rooms add room varchar(255);
UPDATE rooms SET room = 'BEDROOM' where name = 'Bedroom' OR name = 'BEDROOM';
UPDATE rooms SET room = 'PLAYROOM' where name = 'Playroom' OR name = 'PLAYROOM';
UPDATE rooms SET room = 'KITCHEN' where name = 'Kitchen' OR name = 'KITCHEN';
UPDATE rooms SET room = 'BATHROOM' where name = 'Bathroom' OR name = 'BATHROOM';
UPDATE rooms SET room = 'LIVINGROOM' where name = 'Livingroom' OR name = 'LIVINGROOM';
UPDATE rooms SET room = 'YARD' where name = 'Yard' OR name = 'YARD';
UPDATE rooms SET room = 'OTHER' where room is null;
alter table rooms drop column name;
alter table rooms alter column room set not null;

GRANT ALL PRIVILEGES ON TABLE ad to paw;

GRANT ALL PRIVILEGES ON TABLE property_states to paw;


