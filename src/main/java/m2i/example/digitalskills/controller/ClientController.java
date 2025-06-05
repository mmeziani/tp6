// src/main/java/m2i/example/digitalskills/controller/ClientController.java
package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Client;
import m2i.example.digitalskills.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * PUT /api/clients/{id}
     * Body JSON attendu :
     * {
     *   "id": 5,
     *   "name": "Jean Dupont",
     *   "email": "jean@example.com"
     * }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable("id") Long id,
            @RequestBody Client client
    ) {
        client.setId(id);
        Client updated = clientService.updateClient(client);
        return ResponseEntity.ok(updated);
    }
}
