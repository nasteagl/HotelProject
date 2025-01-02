package org.example.Service;

import org.example.Repositories.RoomsTypeRepository;
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
