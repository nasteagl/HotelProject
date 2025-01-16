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
}
