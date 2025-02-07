package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.models.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class RoomRepository  {
    @PersistenceContext
    private  EntityManager entityManager;

    public Room save(Room entity) {
        if(entity.getHotel()==null){
            throw new IllegalArgumentException("Hotel is null");
        }
        if(entity.getRoomType()==null){
            throw new IllegalArgumentException("RoomType is null");
        }
        if(entity.getNumber()<1){
            throw new IllegalArgumentException("Number cannot be less than 1");
        }
        if(entity.getPrice()<1){
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if(entity.getReserved()==null){
            throw new IllegalArgumentException("Reserved is null");
        }
        if(entity.getFloor() <1){
            throw new IllegalArgumentException("Floor cannot be less than 1");
        }
        if (entity.getBeds() < 1) {
            throw new IllegalArgumentException("Numărul de paturi trebuie să fie un număr pozitiv.");
        }

        if (entity.getId_room() == null || entityManager.contains(entity)) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return entity;
    }


    public Room findById(Integer id) {
        return entityManager.find(Room.class, id);

    }

    public List<Room> findAll(){
        return entityManager.createQuery("select r from Room r", Room.class).getResultList();
    }

    public Room update(Room entity) {
      return entityManager.merge(entity);

    }
    public Room delete(Room entity) {
      entityManager.remove(entity);
      return entity;
    }
    public void deleteById(Integer id) {
       Room entity= findById(id);
       delete(entity);
    }


}
