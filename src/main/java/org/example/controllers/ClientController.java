package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.services.ClientService;
import org.example.models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable Integer clientId) {
        Client client = clientService.getClient(clientId);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        if (client != null) {
            clientService.updateClient(client);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Client> deleteClient(@PathVariable Integer clientId) {
        Client client = clientService.getClient(clientId);
        if (client != null) {
            clientService.deleteClient(clientId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
