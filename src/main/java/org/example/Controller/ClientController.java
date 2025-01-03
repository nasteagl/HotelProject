package org.example.Controller;

import org.example.Service.ClientService;
import org.example.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable Integer clientId) {
        Client client = new Client.ClientBuilder().setClientId(clientId).setFirstname("Ion").setLastname("Ionescu").setAge(24).build();
        return client;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody Client client) {
        return client;
    }
}
