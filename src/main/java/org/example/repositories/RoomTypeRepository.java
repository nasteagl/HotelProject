package org.example.repositories;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.models.Room;
import org.example.models.RoomType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Repository
public class RoomTypeRepository  {
    private final EntityManager entityManager;

    public RoomType save(RoomType entity) {
        return entityManager.merge(entity);
    }

    public List<RoomType> findAll() {
        return entityManager.createQuery("from RoomType", RoomType.class).getResultList();
    }

    public RoomType findById(Integer id) {
        return entityManager.find(RoomType.class, id);
    }
    public void deleteRoomType(RoomType entity) {
        entityManager.remove(entity);
    }



    public void deleteByIdRoomType(Integer id) {
        RoomType entity = findById(id);
        entityManager.remove(entity);
    }

    public RoomType UpdateRoomType(RoomType entity) {
        return entityManager.merge(entity);
    }

}
