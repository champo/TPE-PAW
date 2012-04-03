CREATE TABLE Users
(
    Id serial,
    Name varchar(50),
    Surname varchar(50),
    Email varchar(50),
    Phone varchar(20),
    Username varchar(50),
    Password char(40),

    PRIMARY KEY(Id)
);

CREATE UNIQUE INDEX INX_Users_Id on Users(Id);

CREATE UNIQUE INDEX INX_Users_Username on Users(Username);

CREATE TABLE Properties
(
    Id serial,
    PropertyType smallint,
    OperationType smallint,
    Neighbourhood varchar(50),
    Price double precision,
    Rooms smallint,
    IndoorSpace double precision,
    OutDoorSpace double precision,
    Description varchar(1000),
    Sold boolean,
    UserId Integer,

    PRIMARY KEY(Id),
    FOREIGN KEY(UserId) REFERENCES Users(Id)
);

CREATE UNIQUE INDEX INX_Properties_Id on Properties(Id);

CREATE INDEX INX_Properties_UserId on Properties(UserId);

CREATE INDEX INX_Properties_Price on Properties(Price);

CREATE TABLE Pictures
(
    Id serial,
    Name varchar(50),
    Source varchar(150),
    PropertyId Integer,

    PRIMARY KEY(Id),
    FOREIGN KEY(PropertyId) REFERENCES Properties(Id)
);

CREATE INDEX INX_Pictures_Id on Pictures(Id);

CREATE INDEX INX_Pictures_PropertyID on Pictures(PropertyId);

CREATE TABLE Services
(
    Id serial,
    Service varchar(50),
    PropertyId Integer,

    PRIMARY KEY(Id),
    FOREIGN KEY(PropertyId) REFERENCES Properties(Id)
);

CREATE INDEX INX_Services_Id on Services(Id);

CREATE INDEX INX_Services_PropertyID on Services(PropertyId);
