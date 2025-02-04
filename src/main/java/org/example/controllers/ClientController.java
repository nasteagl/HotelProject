package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.ClientDto;
import org.example.services.ClientService;
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
    public ResponseEntity<List<ClientDto>> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Integer clientId) {
        ClientDto client = clientService.getClient(clientId);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto client) {
        return new ResponseEntity<>(clientService.addClient(client), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto client) {
        return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<ClientDto> deleteClient(@PathVariable Integer clientId) {
        ClientDto client = clientService.getClient(clientId);
        if (client != null ) {
            clientService.deleteClient(clientId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<ClientDto> patchClient(@PathVariable Integer clientId, @RequestBody ClientDto clientBody) {
        ClientDto client = clientService.getClient(clientId);
        if (client != null) {
            return new ResponseEntity<>(clientService.patchClient(clientId, clientBody), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
