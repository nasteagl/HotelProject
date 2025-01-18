package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.models.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class ClientRepository {
    private final EntityManager entityManager;

    public void saveClient(Client entity) {
        entityManager.persist(entity);
    }

    public Client findByIdClient(Integer id) {
        return entityManager.find(Client.class, id);
    }

    public List<Client> findAllClients() {
        return entityManager.createQuery("select c from Client c", Client.class).getResultList();
    }

    public void updateClient(Client entity) {
        entityManager.merge(entity);
    }

    public void deleteClient(Client entity) {
        entityManager.remove(entity);
    }

    public void deleteById(Integer id) {
        Client entity = findByIdClient(id);
        deleteClient(entity);
    }
}
