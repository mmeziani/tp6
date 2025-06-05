package m2i.example.digitalskills.repository;

import m2i.example.digitalskills.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis, Long> {
}
