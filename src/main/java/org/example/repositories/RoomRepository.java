package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.models.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class RoomRepository  {
    private final EntityManager entityManager;

    public void save(Room entity) {
        Room savedEntity = entityManager.merge(entity);
        entityManager.persist(savedEntity);
    }

    public Room findById(Integer id) {
        return entityManager.find(Room.class, id);
    }

    public List<Room> findAll(){
        return entityManager.createQuery("select r from Room r", Room.class).getResultList();
    }

    public void update(Room entity) {
       Room updateEntity = entityManager.merge(entity);
       entityManager.merge(updateEntity);
    }
    public void delete(Integer id) {
       Room deleteEntity = entityManager.find(Room.class, id);
       entityManager.remove(deleteEntity);
    }
    public void deleteById(Integer id) {
        Room deleteEntity = entityManager.find(Room.class, id);
        entityManager.remove(deleteEntity);
    }


}
