// src/main/java/m2i/example/digitalskills/repository/LigneCommandeRepository.java
package m2i.example.digitalskills.repository;

import m2i.example.digitalskills.model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
}
