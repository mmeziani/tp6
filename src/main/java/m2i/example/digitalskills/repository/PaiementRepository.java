package m2i.example.digitalskills.repository;

import m2i.example.digitalskills.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
