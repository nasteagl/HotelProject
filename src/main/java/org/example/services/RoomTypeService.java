package org.example.services;

import org.example.repositories.RoomsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {
    public RoomsTypeRepository roomTypeRepository;
    @Autowired
    public RoomTypeService(RoomsTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }
}
