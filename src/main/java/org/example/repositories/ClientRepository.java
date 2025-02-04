package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.models.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Client saveClient(Client entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Client findByIdClient(Integer id) {
        return entityManager.find(Client.class, id);
    }

    public List<Client> findAllClients() {
        return entityManager.createQuery("select c from Client c", Client.class).getResultList();
    }

    public Client updateClient(Client entity) {
        return entityManager.merge(entity);
    }

    public Client deleteClient(Client entity) {
        entityManager.remove(entity);
        return entity;
    }

    public void deleteClientById(Integer id) {
        Client entity = findByIdClient(id);
        deleteClient(entity);
    }
}
