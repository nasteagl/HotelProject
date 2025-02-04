package org.example.repositories;

import jakarta.persistence.PersistenceContext;
import org.example.models.Hotel;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class HotelRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Hotel saveHotel(Hotel entity) {
        Hotel savedEntity = entityManager.merge(entity);
        entityManager.persist(savedEntity);
        return savedEntity;
    }

    public Hotel findByIdHotel(Integer id){
        return entityManager.find(Hotel.class, id);
    }

    public List<Hotel> findAllHotels() {
        return entityManager.createQuery("select h from Hotel h", Hotel.class).getResultList();
    }

    public Hotel updateHotel(Hotel entity) {
        Hotel updatedEntity = entityManager.merge(entity);
        entityManager.merge(updatedEntity);
        return updatedEntity;
    }

    public Hotel deleteHotel(Hotel entity) {
        Hotel deletedEntity = entityManager.merge(entity);
        entityManager.remove(deletedEntity);
        return deletedEntity;
    }

    public Hotel deleteById(Integer id) {
        Hotel entity = findByIdHotel(id);
        deleteHotel(entity);
        return entity;
    }

}