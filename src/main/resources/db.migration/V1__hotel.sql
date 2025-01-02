CREATE TYPE room_type AS ENUM ('Standart', 'DoubleBed', 'SingleBed', 'Penthouse', 'FamilyRoom', 'PresidentialSuite');

drop table public.room cascade;
drop table public.rooms_type cascade;
drop table public.client cascade;
drop table public.hotel cascade;

-- Crearea tabelului Room
CREATE TABLE public.room (
                             id_room SERIAL PRIMARY KEY,
                             floor INT NOT NULL CHECK (floor > 0),
                             number INT NOT NULL CHECK (number > 0),
                             price INT NOT NULL CHECK (price > 0),
                             beds INT NOT NULL CHECK (beds > 0 AND beds <= 10),
                             reserved BOOLEAN NOT NULL DEFAULT FALSE,
                             id_rooms_type INT NOT NULL
);

-- Crearea tabelului RoomTypes
CREATE TABLE public.rooms_type (
                            id_rooms_type SERIAL PRIMARY KEY,
                            current_type room_type NOT NULL,
                            id_room INT NOT NULL,
                            hotel_id INT NOT NULL,
                            FOREIGN KEY (id_room) REFERENCES public.room(id_room) ON DELETE CASCADE
);

-- Crearea tabelului Client
CREATE TABLE public.client (
                               client_id SERIAL PRIMARY KEY,
                               firstname VARCHAR(100),
                               lastname VARCHAR(100),
                               age INT CHECK (age > 0),
                               nr_pers INT CHECK (nr_pers > 0),
                               checkin VARCHAR(100),
                               checkout VARCHAR(100),
                               email VARCHAR(100),
                               phone_number VARCHAR(15),
                               hotel_id INT NOT NULL
);

-- Crearea tabelului Hotel
CREATE TABLE public.hotel (
                       hotel_id SERIAL PRIMARY KEY,
                       hotel_address VARCHAR(100) NOT NULL CHECK (LENGTH(hotel_address) > 5),
                       hotel_city VARCHAR(100) NOT NULL CHECK (LENGTH(hotel_city) > 1),
                       hotel_country VARCHAR(100) NOT NULL CHECK (LENGTH(hotel_country) > 1),
                       hotel_phone VARCHAR(15),
                       hotel_email VARCHAR(100) ,
                       client_id INT NOT NULL REFERENCES public.client(client_id),
                       rooms_type_id INT NOT NULL REFERENCES public.rooms_type(id_rooms_type)
);


