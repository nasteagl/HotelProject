package org.example.controllers;

import org.example.services.ClientService;
import org.example.models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin // daca candva vom conecta cu front sa nu apara probleme
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }



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


//    Fara ResponseEntity (daca vrem sa avem custom responsuri (de exemplu alt text pentru raspuns 200))
//
//    @GetMapping
//    public List<Client> getClients() {
//        return clientService.getClients();
//    }
//
//    @GetMapping("/{clientId}")
//    public Client getClient(@PathVariable Integer clientId) {
//        return clientService.getClient(clientId);
//    }
//
//    @PostMapping
//    public void addClient(@RequestBody Client client) {
//        clientService.addClient(client);
//    }
//
//    @PutMapping
//    public void updateClient(@RequestBody Client client) {
//        clientService.updateClient(client);
//    }
//    // http://localhost:8090/client?clientId=2&new_age=34 (pentru a testa patch)
//    @PatchMapping
//    public void patchClient(@RequestParam Integer clientId, @RequestParam Integer new_age) {
//        clientService.patchClient(clientId, new_age);
//    }
//
//    @DeleteMapping("/{clientId}")
//    public void deleteClient(@PathVariable Integer clientId) {
//        clientService.deleteClient(clientId);
//    }
}
