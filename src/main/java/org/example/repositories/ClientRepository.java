package org.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.models.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}

//import jakarta.persistence.EntityManager;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.example.models.Client;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Transactional
//@Repository
//@RequiredArgsConstructor
//public class ClientRepository {
//    private final EntityManager entityManager;
//
//    public void save(Client entity) {
//        entityManager.persist(entity);
//    }
//
//    public Client findById(Long id) {
//        return entityManager.find(Client.class, id);
//    }
//
//    public List<Client> findAll() {
//        return entityManager.createQuery("select c from Client c", Client.class).getResultList();
//    }
//}
