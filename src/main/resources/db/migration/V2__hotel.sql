ALTER TABLE hotel_schema.room
    ADD room_type_enum VARCHAR(255);

ALTER TABLE hotel_schema.rooms_type
    DROP COLUMN room_type_enum;