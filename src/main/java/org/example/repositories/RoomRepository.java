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
        entityManager.persist(entity);
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
