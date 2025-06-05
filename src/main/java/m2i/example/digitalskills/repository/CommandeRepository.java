// src/main/java/m2i/example/digitalskills/repository/CommandeRepository.java
package m2i.example.digitalskills.repository;

import m2i.example.digitalskills.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
