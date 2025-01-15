package org.example.services;

import org.example.dto.ClientDto;
import org.example.models.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.example.repositories.ClientRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    public List<ClientDto> getClients() {
        return clientRepository.findAll()
                .stream()
                .map(el -> modelMapper.map(el, ClientDto.class))
                .collect(Collectors.toList());
    }

    public ClientDto getClient(Integer clientId) {
        return modelMapper.map(clientRepository.findById(clientId), ClientDto.class);
    }

    public void addClient(Client client) {
        clientRepository.save(client); // daca nu exista asa obiect, el va crea
    }

    public void updateClient(Client client) {
        clientRepository.save(client); // daca exista el va schimba
    }

    public void deleteClient(Integer clientId) {
        modelMapper.map(clientRepository.findById(clientId), clientRepository);
        clientRepository.deleteById(clientId);
    }

//    Cu dto si modelaMapper
//
//    public void addClient(ClientDto client) {
//        modelMapper.map(client, clientRepository);
//        clientRepository.save(modelMapper.map(client, Client.class));
//    }
//
//    public void updateClient(ClientDto client) {
//        modelMapper.map(client, clientRepository);
//        clientRepository.save(modelMapper.map(client, Client.class));
//    }
//
//    public void deleteClient(Integer clientId) {
//        modelMapper.map(clientRepository.findById(clientId), clientRepository);
//        clientRepository.deleteById(clientId);
//    }

//    Fără dto

//    public List<Client> getClients() {
//        return clientRepository.findAll();
//    }

//    public Client getClient(Integer clientId) {
//        return clientRepository.findById(clientId).orElse(null);
//    }
//
//    public void addClient(Client client) {
//        clientRepository.save(client); // daca nu exista asa obiect, el va crea
//    }
//
//    public void updateClient(Client client) {
//        clientRepository.save(client); // daca exista el va schimba
//    }
//
//    public void deleteClient(Integer clientId) {
//        clientRepository.deleteById(clientId);
//    }

//    Cu dto fara modelMapper

//    public List<ClientDto> getClients() {
//        return clientRepository.findAll()
//                .stream()
//                .map(client -> new ClientDto(
//                        client.getClient_id(),
//                        client.getFirstname(),
//                        client.getLastname(),
//                        client.getAge(),
//                        client.getPhone_number(),
//                        client.getEmail()
//                )).collect(Collectors.toList());
//    }



//    Pentru @PatchMapping
//    public void patchClient(Integer clientId, Integer new_age) {
//        clientRepository.findById(clientId).ifPresent(client -> {client.setAge(new_age);});
//    }

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
//
//    public void patchClient(Integer clientId, Integer new_age) {
//        clients.get(clients.get(clientId - 1).getClientId() - 1).setAge(new_age);
//    }
//
//    public void deleteClient(Integer clientId) {
//        clients.remove(clients.get(clientId - 1).getClientId() - 1);
//    }
}
