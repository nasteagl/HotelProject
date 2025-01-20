package org.example.repositories;

import org.example.models.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;


@Transactional
@Repository
@RequiredArgsConstructor
public class HotelRepository {
    private final EntityManager entityManager;

    public void saveHotel(Hotel entity) {
        Hotel savedEntity = entityManager.merge(entity);
        entityManager.persist(savedEntity);
    }

    public Hotel findByIdHotel(Integer id){
        return entityManager.find(Hotel.class, id);
    }

    public List<Hotel> findAllHotels() {
        return entityManager.createQuery("select h from Hotel h", Hotel.class).getResultList();
    }

    public void updateHotel(Hotel entity) {
        Hotel updatedEntity = entityManager.merge(entity);
        entityManager.merge(updatedEntity);
    }

    public void deleteHotel(Hotel entity) {
        Hotel deletedEntity = entityManager.merge(entity);
        entityManager.remove(deletedEntity);
    }

    public void deleteById(Integer id) {
        Hotel entity = findByIdHotel(id);
        deleteHotel(entity);
    }

}
