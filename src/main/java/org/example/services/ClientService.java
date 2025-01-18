package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.ClientDto;
import org.example.models.Client;
import org.example.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public List<ClientDto> getClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDto::fromClient) // obj -> ClientDto.fromClient(obj)
                .toList();
    }

    public ClientDto getClient(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (null == client) {
            return null;
        } else {
            return ClientDto.fromClient(client);
        }
    }
    public void addClient(ClientDto client) {
        clientRepository.save(ClientDto.fromClientDto(client));
    }

    public void updateClient(ClientDto client) {
        clientRepository.save(ClientDto.fromClientDto(client));
    }

    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }

    public void patchClient(Integer clientId, ClientDto clientDto) {
        Client client = clientRepository.findById(clientId).get();

        if (clientDto.getFirstname() != null && !clientDto.getFirstname().isEmpty()) {
            client.setFirstname(clientDto.getFirstname());
        }
        if (clientDto.getLastname() != null && !clientDto.getLastname().isEmpty()) {
            client.setLastname(clientDto.getLastname());
        }
        if (clientDto.getAge() != null && clientDto.getAge() > 0 && clientDto.getAge() < 120) {
            client.setAge(clientDto.getAge());
        }
        if (clientDto.getNrPersons() != null && clientDto.getNrPersons() > 0 && clientDto.getNrPersons() < 10) {
            client.setNrPersons(clientDto.getNrPersons());
        }
        if (clientDto.getCheckIn() != null) {
            client.setCheckIn(clientDto.getCheckIn());
        }
        if (clientDto.getCheckOut() != null) {
            client.setCheckOut(clientDto.getCheckOut());
        }
        if (clientDto.getPhoneNumber() != null && !clientDto.getPhoneNumber().isEmpty()) {
            client.setPhoneNumber(clientDto.getPhoneNumber());
        }
        if (clientDto.getEmail() != null && !clientDto.getEmail().isEmpty()) {
            client.setEmail(clientDto.getEmail());
        }
        clientRepository.save(client);
    }
}
