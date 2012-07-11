INSERT INTO Users (name, surname, email, phone, username, password, realestatename, logoextension, type) VALUES ('Dario','Susnisky','dario.susnisky@gmail.com'
    ,'4234-8124','dario.susnisky','6764F7CB5C3FC4612AA26501996CAA298340D002885597E2AA6E8417BECA529F', 'Inmobiliaria Santander', '.jpg', 'REAL_ESTATE');

INSERT INTO Users (name, surname, email, phone, username, password, realestatename, logoextension, type) VALUES ('Juan','Civile','juan.civile@gmail.com',
    '4123-5678','juan.civile','59B0914D9CA0BF908C7FDA6CA7A5E036217AF427AA00CAB2B7FDB0AD98E34CB4', 'champo properties', '.jpg', 'REAL_ESTATE');

INSERT INTO Users (name, surname, email, phone, username, password, type) VALUES ('Alvaro','Crespo','alvaro.crespo@gmail.com',
    '4987-6543','alvaro.crespo','21110D3F9C3606318C324A51DEC4CEC8F9CCA66E922C7EB1574F09C0751BEA16', 'REGULAR');

INSERT INTO Users (name, surname, email, phone, username, password, type) VALUES ('Dario','Sneider','dario.sneider@gmail.com',
    '4000-0000','dario.sneider','0532F3801F34EF0FE7E99F03D6F8811D18F1323EF62EF44E49D93785B51FA992', 'REGULAR');

INSERT INTO Users (name, surname, email, phone, username, password, type) VALUES ('Pepe','Grillo','pepe.grillo@gmail.com',
    '4111-1111','pepe.grillo','502A889450C4A10508821F15EA9D231FAC02720DEE97A6A119F0C41885AB9D35', 'REGULAR');

INSERT INTO Users (name, surname, email, phone, username, password, type) VALUES ('Han','Solo','han.solo-kpo@gmail.com',
    '4222-2222','han.solo','5DCE4B4B77A5F97C16EB8D028B1AC921A17D267535B84E298992ECD6828F7CD0', 'REGULAR');

INSERT INTO Users (name, surname, email, phone, username, password, type) VALUES ('Indiana','Jones','indiana.jones@gmail.com',
    '4333-3333','indiana.jones','4D52D9592F37A53EDD3AACDE2C7DC998ED36D16F6DD270DFC63418A7F9703656', 'REGULAR');

INSERT INTO Users (name, surname, email, phone, username, password, type) VALUES ('Ned','Stark','ned.stark@gmail.com',
    '4444-4444','ned.stark','999F45531AFCAE84E71EEB1335E547428E4B6DB639037647B552FDA8C00C3F9C', 'REGULAR');

INSERT INTO Users (name, surname, email, phone, username, password, type) VALUES ('Frodo','Baggins','frodo.baggins@gmail.com',
    '4555-5555','frodo.baggins','FA7EFD8DCF640ADEEEFB2F5EC24EF9B1CDC3AAECD8654549AF3534C3FB8635AD', 'REGULAR');

INSERT INTO Users (name, surname, email, phone, username, password, type) VALUES ('Barney','Stinson','awesome@gmail.com',
    '4666-6666','barney.stinson','F6A20F6639C13FD2E46F61883095A6E97EE30E06D88D5287D22E8BE45591E394', 'REGULAR');

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (1,1,'Doblas 344, Capital Federal, Argentina','Caballito',1500,5,140,5,'My great house',5,TRUE,1,FALSE,0, '$', false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (1,0,'Ayacucho 867, Capital Federal, Argentina','Recoleta',650,3,90,0,'My new residence',0,TRUE,2,FALSE,0, '$', false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (0,1,'Callao 832, Capital Federal, Argentina','The north',100000,72,4000,3000,'The Winterfell castle',10000,TRUE,8,FALSE,0, 'U$S', false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (0,0,'Malabia 2166, Capital Federal, Argentina','The wall',500000,150,5000,10000,'Castle Black in the wall',8000,TRUE,8,FALSE,0, '$', false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (0,0,'Paseo Colon 500, Capital Federal, Argentina','The Riverlands',50000,54,3400,2600,'Riverun castle',6500,true,8,false,0, 'U$S', false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (1,1,'Juramento 2399, Capital Federal, Argentina','NY',1000,3,85,0,'Its near Mclarens',10,TRUE,10,false,0, '$', false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (1,0,'Mexico 167, capital Federal, Argentina','Long island',3000,5,250,100,'Marshall and lilys place in long island',30,true,10,false,0,'U$S',false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (0,1,'Nueva York 3414, Capital Federal, Argentina','Space!',10000,5,500,0,'The one and only Millenium Falcon',15,TRUE,6,false,0,'$',false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (0,0,'Bulnes 172, Capital Federal, Argentina','Almagro',1000,3,110,10,'',3,true,5,false,0,'U$S',false);

INSERT INTO Properties (propertyType, operationType, address, neighbourhood, price, numrooms, indoorSpace, outDoorSpace,
    description, antiquity, published, user_id, reserved, visited, currency, sold) VALUES
    (0,0,'Reconquista 328, Capital Federal, Buenos Aires','Iron Islands',900000,43,10000,5000,'Pyke',10000,true,8,FALSE,0,'$',false);

INSERT INTO property_services (property_id, services) VALUES (1,'SECURITY');

INSERT INTO property_services (property_id, services) VALUES (1,'POOL');

INSERT INTO property_services (property_id, services) VALUES (1,'TENNIS');

INSERT INTO property_services (property_id, services) VALUES (2,'SOLARIUM');

INSERT INTO property_services (property_id, services) VALUES (2,'BARBECUE');

INSERT INTO property_services (property_id, services) VALUES (2,'PHONE');

INSERT INTO property_services (property_id, services) VALUES (3,'LAUNDRY');

INSERT INTO property_services (property_id, services) VALUES (3,'CABLE');

INSERT INTO property_services (property_id, services) VALUES (3,'PADDLE');

INSERT INTO property_services (property_id, services) VALUES (4,'LOUNGE');

INSERT INTO property_services (property_id, services) VALUES (4,'SECURITY');

INSERT INTO property_services (property_id, services) VALUES (4,'POOL');

INSERT INTO property_services (property_id, services) VALUES (5,'TENNIS');

INSERT INTO property_services (property_id, services) VALUES (5,'SOLARIUM');

INSERT INTO property_services (property_id, services) VALUES (5,'BARBECUE');

INSERT INTO property_services (property_id, services) VALUES (6,'PHONE');

INSERT INTO property_services (property_id, services) VALUES (6,'LAUNDRY');

INSERT INTO property_services (property_id, services) VALUES (6,'CABLE');

INSERT INTO property_services (property_id, services) VALUES (7,'PADDLE');

INSERT INTO property_services (property_id, services) VALUES (7,'LOUNGE');

INSERT INTO property_services (property_id, services) VALUES (7,'SECURITY');

INSERT INTO property_services (property_id, services) VALUES (8,'POOL');

INSERT INTO property_services (property_id, services) VALUES (9,'TENNIS');

INSERT INTO property_services (property_id, services) VALUES (10,'SOLARIUM');

INSERT INTO rooms (property_id, room, length, width) VALUES (2, 'BATHROOM', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (2, 'KITCHEN', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (2, 'BEDROOM', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (2, 'YARD', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (3, 'BATHROOM', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (3, 'KITCHEN', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (4, 'OTHER', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (4, 'KITCHEN', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (5, 'BATHROOM', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (5, 'KITCHEN', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (6, 'BATHROOM', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (6, 'KITCHEN', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (7, 'KITCHEN', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (7, 'YARD', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (8, 'BATHROOM', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (8, 'BEDROOM', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (9, 'YARD', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (9, 'BATHROOM', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (10, 'OTHER', 3, 3);

INSERT INTO rooms (property_id, room, length, width) VALUES (10,'BATHROOM', 3, 3);

INSERT INTO ad (weight, url, clientname) VALUES (3, 'http://www.filebuzz.com/software_screenshot/full/main_menu.jpg' , 'Master Client');

INSERT INTO ad (weight, url, clientname) VALUES (9, 'http://affairsofthevine.com/wp-content/uploads/2012/01/niners-helmet.jpg' , 'The niners');

INSERT INTO ad (weight, url, clientname) VALUES (8, 'http://farm1.static.flickr.com/84/249886462_cbc5e8bc9a.jpg' , 'Raining man');

INSERT INTO ad (weight, url, clientname) VALUES (20, 'http://3.bp.blogspot.com/_WUre9Wi0o1o/SV1qC_xYePI/AAAAAAAAABc/CjFOrUowCM4/s1600/dreamstimesmall_4373486.jpg' , 'Super priority');

INSERT INTO ad (weight, url, clientname) VALUES (1, 'http://asrs.arc.nasa.gov/publications/callback/318_3.gif' , 'Minimum priority');

INSERT INTO ad (weight, url, clientname) VALUES (12, 'http://4.bp.blogspot.com/_b3dnYswRsNo/TOqQCvRt7iI/AAAAAAAAAOs/_n5CEbUlBRs/s1600/numbers.jpg' , 'Great number!');

INSERT INTO ad (weight, url, clientname) VALUES (4, 'http://2.bp.blogspot.com/-QoLGvkSt95M/T3LQOwpuamI/AAAAAAAACAI/cNNZc76biNo/s640/im_ok.GIF' , 'I am okey!');

INSERT INTO ad (weight, url, clientname) VALUES (2, 'http://cdn.fd.uproxx.com/wp-content/uploads/2012/03/twins.jpg' , 'Twins');

INSERT INTO ad (weight, url, clientname) VALUES (6, 'http://upload.wikimedia.org/wikipedia/en/9/99/66ers_cap.PNG' , '66ers');

INSERT INTO ad (weight, url, clientname) VALUES (5, 'http://www.spiritandflesh.com/digital-art-union-the-perfection-Aquarian-Awakening-magnified-100Billiontimes.jpg' , 'Perfection');

