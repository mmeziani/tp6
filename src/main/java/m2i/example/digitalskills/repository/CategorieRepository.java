package m2i.example.digitalskills.repository;

import m2i.example.digitalskills.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
