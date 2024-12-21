-- Crearea tabelului Client
CREATE TABLE Client (
                        client_id SERIAL PRIMARY KEY,
                        firstname VARCHAR(100),
                        lastname VARCHAR(100),
                        age INT CHECK (age > 0),
                        nr_pers INT CHECK (nr_pers > 0),
                        checkin DATE,
                        checkout DATE,
                        email VARCHAR(100),
                        phone_number VARCHAR(15)
);

-- Crearea tabelului RoomTypes
CREATE TABLE RoomTypes (
                           rooms_type_id SERIAL PRIMARY KEY,
                           room_type VARCHAR(50) NOT NULL UNIQUE
);

-- Inserarea valorilor în tabelul RoomTypes
INSERT INTO RoomTypes (room_type)
VALUES
    ('Standard'),
    ('DoubleBed'),
    ('SingleBed'),
    ('Penthouse'),
    ('FamilyRoom'),
    ('PresidentialSuite');

-- Crearea tabelului Room
CREATE TABLE Room (
                      id_room SERIAL PRIMARY KEY,
                      room_floor INT NOT NULL CHECK (room_floor > 0),
                      room_number INT NOT NULL CHECK (room_number > 0),
                      price INT NOT NULL CHECK (price > 0),
                      beds INT NOT NULL CHECK (beds > 0 AND beds <= 10),
                      reserved BOOLEAN NOT NULL DEFAULT FALSE,
                      rooms_type_id INT NOT NULL REFERENCES RoomTypes(rooms_type_id)
);

-- Crearea tabelului Hotel
CREATE TABLE Hotel (
                       hotel_id SERIAL PRIMARY KEY,
                       hotel_address VARCHAR(100) NOT NULL CHECK (LENGTH(hotel_address) > 5),
                       hotel_city VARCHAR(100) NOT NULL CHECK (LENGTH(hotel_city) > 1),
                       hotel_country VARCHAR(100) NOT NULL CHECK (LENGTH(hotel_country) > 1),
                       hotel_phone VARCHAR(15),
                       hotel_email VARCHAR(100) ,
                       client_id INT NOT NULL REFERENCES Client(client_id),
                       rooms_type_id INT NOT NULL REFERENCES RoomTypes(rooms_type_id)
);