package org.example.repositories;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class RoomTypeRepository  {
    private final EntityManager entityManager;

    public void save(RoomType entity) {
        RoomType saveEntity = entityManager.merge(entity);
        entityManager.persist(saveEntity);
    }

    public List<RoomType> findAll() {
        return entityManager.createQuery("from RoomType", RoomType.class).getResultList();
    }

    public RoomType findById(Integer id) {
        return entityManager.find(RoomType.class, id);
    }
    public void delete(Integer id) {
        RoomType entity = entityManager.find(RoomType.class, id);
    }
    public void deleteById(Integer id) {
        RoomType entity = entityManager.find(RoomType.class, id);
    }
}
