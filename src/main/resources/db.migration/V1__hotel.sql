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

CREATE TYPE room_type AS ENUM ('Standart', 'DoubleBed', 'SingleBed', 'Penthouse', 'FamilyRoom', 'PresidentialSuite');


-- Crearea tabelului RoomTypes
CREATE TABLE rooms_type (
                            id_rooms_type SERIAL PRIMARY KEY,
                            current_type room_type NOT NULL,
                            id_room INT NOT NULL,
                            hotel_id INT NOT NULL,
                            FOREIGN KEY (id_room) REFERENCES room(id_room) ON DELETE CASCADE,
                            FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id) ON DELETE CASCADE
);


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

Drop table client cascade;