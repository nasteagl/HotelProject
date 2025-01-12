package org.example.controllers;

import org.example.services.ClientService;
import org.example.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;



    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable Integer clientId) {
        return clientService.getClient(clientId);
    }

    @PostMapping
    public void addClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

    @PutMapping
    public void updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }
    // http://localhost:8090/client?clientId=2&new_age=34 (pentru a testa patch)
    @PatchMapping
    public void patchClient(@RequestParam Integer clientId, @RequestParam Integer new_age) {
        clientService.patchClient(clientId, new_age);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
    }
}
