// src/main/java/m2i/example/digitalskills/service/ClientService.java
package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Client;
import java.util.Optional;

public interface ClientService {
    Client updateClient(Client client);
    Optional<Client> findById(Long id);
}
