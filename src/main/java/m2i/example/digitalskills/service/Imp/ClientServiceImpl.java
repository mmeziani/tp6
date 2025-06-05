// src/main/java/m2i/example/digitalskills/service/Imp/ClientServiceImpl.java
package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.Client;
import m2i.example.digitalskills.repository.ClientRepository;
import m2i.example.digitalskills.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }
}
