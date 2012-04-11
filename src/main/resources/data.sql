INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Dario','Susnisky','dario.susnisky@gmail.com'
    ,'4234-8124','dario.susnisky','6764F7CB5C3FC4612AA26501996CAA298340D002885597E2AA6E8417BECA529F');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Juan','Civile','juan.civile@gmail.com',
    '4123-5678','juan.civile','59B0914D9CA0BF908C7FDA6CA7A5E036217AF427AA00CAB2B7FDB0AD98E34CB4');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Alvaro','Crespo','alvaro.crespo@gmail.com',
    '4987-6543','alvaro.crespo','21110D3F9C3606318C324A51DEC4CEC8F9CCA66E922C7EB1574F09C0751BEA16');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Dario','Sneider','dario.sneider@gmail.com',
    '4000-0000','dario.sneider','0532F3801F34EF0FE7E99F03D6F8811D18F1323EF62EF44E49D93785B51FA992');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Pepe','Grillo','pepe.grillo@gmail.com',
    '4111-1111','pepe.grillo','502A889450C4A10508821F15EA9D231FAC02720DEE97A6A119F0C41885AB9D35');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Han','Solo','han.solo-kpo@gmail.com',
    '4222-2222','han.solo','5DCE4B4B77A5F97C16EB8D028B1AC921A17D267535B84E298992ECD6828F7CD0');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Indiana','Jones','indiana.jones@gmail.com',
    '4333-3333','indiana.jones','4D52D9592F37A53EDD3AACDE2C7DC998ED36D16F6DD270DFC63418A7F9703656');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Ned','Stark','ned.stark@gmail.com',
    '4444-4444','ned.stark','999F45531AFCAE84E71EEB1335E547428E4B6DB639037647B552FDA8C00C3F9C');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Frodo','Baggins','frodo.baggins@gmail.com',
    '4555-5555','frodo.baggins','FA7EFD8DCF640ADEEEFB2F5EC24EF9B1CDC3AAECD8654549AF3534C3FB8635AD');

INSERT INTO Users (name, surname, email, phone, username, password) VALUES ('Barney','Stinson','awesome@gmail.com',
    '4666-6666','barney.stinson','F6A20F6639C13FD2E46F61883095A6E97EE30E06D88D5287D22E8BE45591E394');

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (1,1,'Doblas 344, Capital Federal, Argentina','Caballito',1500,5,140,5,'My great house',5,TRUE,TRUE,TRUE,TRUE,FALSE,TRUE,TRUE,1);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (1,0,'Ayacucho 867, Capital Federal, Argentina','Recoleta',650,3,90,0,'My new residence',0,FALSE,TRUE,FALSE,FALSE,FALSE,FALSE,TRUE,2);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (0,1,'Callao 832, Capital Federal, Argentina','The north',100000,72,4000,3000,'The Winterfell castle',10000,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,8);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (0,0,'Malabia 2166, Capital Federal, Argentina','The wall',500000,150,5000,10000,'Castle Black in the wall',8000,FALSE,TRUE,FALSE,FALSE,TRUE,TRUE,TRUE,8);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (0,0,'Paseo Colon 500, Capital Federal, Argentina','The Riverlands',50000,54,3400,2600,'Riverun castle',6500,FALSE,TRUE,TRUE,TRUE,TRUE,TRUE,FALSE,8);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (1,1,'Juramento 2399, Capital Federal, Argentina','NY',1000,3,85,0,'Its near Mclarens',10,TRUE,TRUE,FALSE,FALSE,FALSE,FALSE,TRUE,10);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (1,0,'Mexico 167, capital Federal, Argentina','Long island',3000,5,250,100,'Marshall and lilys place in long island',30,TRUE,TRUE,TRUE,FALSE,TRUE,TRUE,FALSE,10);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (0,1,'Nueva York 3414, Capital Federal, Argentina','Space!',10000,5,500,0,'The one and only Millenium Falcon',15,TRUE,TRUE,FALSE,FALSE,FALSE,FALSE,TRUE,6);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (0,0,'Bulnes 172, Capital Federal, Argentina','Almagro',1000,3,110,10,'',3,TRUE,TRUE,FALSE,FALSE,FALSE,FALSE,FALSE,5);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, rooms, indoorSpace, outDoorSpace,
    description, antiquity, cable, phone, pool, lounge, paddle, barbecue, published, userId) VALUES
    (0,0,'Reconquista 328, Capital Federal, Buenos Aires','Iron Islands',900000,43,10000,5000,'Pyke',10000,FALSE,FALSE,TRUE,TRUE,TRUE,TRUE,FALSE,8);

INSERT INTO Pictures (name, propertyId, extension) VALUES ('Winterfell',3,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('Our moat',3,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('A painting of Winterfell',3,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('Castle Black from the distance',4,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('The Falcon, flying',8,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('A map of the falcon',8,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('Teds apartmentent, talking about the belt',6,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('An accident on the house',7,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('The bedroom',1,'.jpg');

INSERT INTO Pictures (name, propertyId, extension) VALUES ('The living room',1,'.jpg');
