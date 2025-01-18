package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.models.Client;
import org.example.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(Integer clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }
    public void addClient(Client client) {
        clientRepository.save(client); // daca nu exista asa obiect, el va crea
    }

    public void updateClient(Client client) {
        clientRepository.save(client); // daca exista el va schimba
    }

    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}
