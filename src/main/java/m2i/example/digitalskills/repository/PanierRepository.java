// src/main/java/m2i/example/digitalskills/repository/PanierRepository.java
package m2i.example.digitalskills.repository;

import m2i.example.digitalskills.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanierRepository extends JpaRepository<Panier, Long> {
    // Renvoie un panier existant pour ce client (on suppose qu’il n’y en a qu’un active)
    Optional<Panier> findByClientId(Long clientId);
}
