package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.Categorie;
import m2i.example.digitalskills.repository.CategorieRepository;
import m2i.example.digitalskills.service.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImp implements CategorieService {
    private CategorieRepository categorieRepository;

    public CategorieServiceImp(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public Categorie createCategorie(Categorie categorie){
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(Categorie categorie){
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(Categorie categorie){
        categorieRepository.delete(categorie);
    }

    public List<Categorie> getCategories() {
        return categorieRepository.findAll();
    }

    public Categorie findOne(Long id){
        Optional<Categorie> categorie = categorieRepository.findById(id);
        return categorie.orElseThrow(() -> new RuntimeException("Categorie n'existe pas"));
    }
}
