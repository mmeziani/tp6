package m2i.example.digitalskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import m2i.example.digitalskills.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}