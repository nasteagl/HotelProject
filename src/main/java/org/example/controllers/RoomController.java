package org.example.controllers;


import org.example.dto.ClientDto;
import org.example.dto.RoomDto;
import org.example.models.Room;
import org.example.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("room")
public class RoomController {

 //@Autowired
 private final  RoomService roomService;

 public RoomController(RoomService roomService) {
     this.roomService = roomService;
 }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(),HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Integer roomId) {
        RoomDto room = roomService.getRoomById(roomId);
        if(room != null){
            return new ResponseEntity<>(room,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void addRoom(@RequestBody Room room){
     roomService.addRoom(room);
    }

    @PutMapping
    public void updateRoom(@RequestBody Room room){
     roomService.updateRoom(room);
    }

    @DeleteMapping("/{roomId}/")
    public void deleteRoomById(@PathVariable Integer roomId) {
     roomService.deleteRoom(roomId);
    }

}









    /// Prin intermediul la Array deja creat, lipsa legaturii cu baza de date
//    ArrayList<Room> rooms = new ArrayList<>() {
//        {
//            add(new Room.RoomBuilder().setIdRoom(1).setFloor(1).setNumber(111).setPrice(1000).setBeds(2).setReserved(false).build());
//            add(new Room.RoomBuilder().setIdRoom(2).setFloor(2).setNumber(222).setPrice(2000).setBeds(3).setReserved(false).build());
//            add(new Room.RoomBuilder().setIdRoom(3).setFloor(3).setNumber(333).setPrice(3000).setBeds(4).setReserved(false).build());
//        }
//    };
//
//    @GetMapping
//    public ResponseEntity<ArrayList<Room>> getRooms() {
//        return new ResponseEntity<>(rooms, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Room> getRoomById(@PathVariable Integer id) {
//        if (id < 0 || id > rooms.size()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    return new ResponseEntity<>(rooms.get(id), HttpStatus.OK);
//    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Room> updateRoom(@PathVariable Integer id, @RequestBody Room room) {
//       if(id < 0 || id>=rooms.size()){
//           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//       Room existingRoom = rooms.get(id);
//       existingRoom.setPrice(room.getPrice());
//       existingRoom.setBeds(room.getBeds());
//       existingRoom.setReserved(room.isReserved());
//       existingRoom.setFloor(room.getFloor());
//       existingRoom.setNumber(room.getNumber());
//       return new ResponseEntity<>(rooms.get(id), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Room> deleteRoom(@PathVariable Integer id) {
//        if (id < 0 || id > rooms.size()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        rooms.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<Room> patchRoom(@PathVariable Integer id, @RequestBody Room room) {
//        if (id < 0 || id > rooms.size()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        Room existingRoom = rooms.get(id);
//        if(room.getFloor() !=0) existingRoom.setFloor(room.getFloor());
//        if(room.getNumber() !=0) existingRoom.setNumber(room.getNumber());
//        if(room.getPrice() !=0) existingRoom.setPrice(room.getPrice());
//        if(room.getBeds() !=0) existingRoom.setBeds(room.getBeds());
//        if(room.isReserved()!= existingRoom.isReserved()) existingRoom.setReserved(room.isReserved());
//        return new ResponseEntity<>(rooms.get(id), HttpStatus.OK);
//    }




