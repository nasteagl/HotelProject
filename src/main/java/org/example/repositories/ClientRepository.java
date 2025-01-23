package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    @PersistenceContext
    private EntityManager entityManager;

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

    public List<Client> findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);
        cq.select(root);
        cq.where(cb.equal(root.get("name"), name));
        return entityManager.createQuery(cq).getResultList();
    }

    public void deleteAll() {
        entityManager.createQuery("delete from Client").executeUpdate();
    }
}
