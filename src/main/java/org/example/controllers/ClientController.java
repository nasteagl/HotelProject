package org.example.controllers;

import org.example.services.ClientService;
import org.example.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    ArrayList<Client> clients = new ArrayList<>() {
        {
            add(new Client.ClientBuilder().setClientId(1).setFirstname("Ion").setLastname("Ionescu").setAge(24).build());
            add(new Client.ClientBuilder().setClientId(2).setFirstname("Andrei").setLastname("Antonescu").setAge(27).build());
            add(new Client.ClientBuilder().setClientId(3).setFirstname("Ana").setLastname("Mariana").setAge(22).build());
        }
    };

    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable Integer clientId) {
        return clients.get(clientId);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Client createClient(@RequestBody Client client) {
//        return client;
//    }

    @PostMapping("/{clientId}")
    public Client increaseAge(@PathVariable Integer clientId) {
        clients.get(clientId).setAge(clients.get(clientId).getAge() + 1);
        return clients.get(clientId);
    }

    @PatchMapping("/age")
    public Client changeAge(@RequestParam(name="new-value")int newValue) {
        clients.get(1).setAge(newValue);
        return clients.get(1);
    }

    @PutMapping("/update")
    public Client updateClient(@RequestBody Client client) {
        clients.get(client.getClientId() - 1).setFirstname(client.getFirstname());
        return clients.get(client.getClientId() - 1);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@RequestParam(name="clientId") Integer clientId) {
        clients.remove(clientId);
    }
}
