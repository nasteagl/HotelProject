package org.example.services;

import org.example.models.Client;
import org.springframework.stereotype.Service;
import org.example.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

//    @Autowired
//    public ClientService(ClientRepository clientRepository) {
//        this.clientRepository = clientRepository;
//    }

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

    public void patchClient(Integer clientId, Integer new_age) {
        clientRepository.findById(clientId).ifPresent(client -> {client.setAge(new_age);});
    }

    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }


//    Veriunea cu lista, inainte de a conecta cu repositoriu (pentru experimente)

//    List<Client> clients = new ArrayList<>(Arrays.asList(
//            new Client.ClientBuilder().setClientId(1).setFirstname("Ion").setLastname("Ionescu").setAge(24).build(),
//            new Client.ClientBuilder().setClientId(2).setFirstname("Andrei").setLastname("Antonescu").setAge(27).build(),
//            new Client.ClientBuilder().setClientId(3).setFirstname("Ana").setLastname("Mariana").setAge(22).build()
//    ));

//    public List<Client> getClients() {
//        return clients;
//    }
//
//    public Client getClient(Integer clientId) {
//
//        return clients.stream()
//                .filter(c -> c.getClientId() == clientId) // cauta id
//                .findFirst() // se opreste de cautat cand gaseste primul id ce satisface conditia
//                .orElse(null); // sau .get() ca nimic sa nu intoarca, .orElse daca nu a gasit nimic
//    }
//
//    public void addClient(Client client) {
//        clients.add(client);
//    }
//
//    public void updateClient(Client client) {
//        clients.set(clients.get(client.getClientId() - 1).getClientId() - 1, client);
//    }
//
//    public void patchClient(Integer clientId, Integer new_age) {
//        clients.get(clients.get(clientId - 1).getClientId() - 1).setAge(new_age);
//    }
//
//    public void deleteClient(Integer clientId) {
//        clients.remove(clients.get(clientId - 1).getClientId() - 1);
//    }
}
