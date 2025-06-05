package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Categorie;

import java.util.List;

public interface CategorieService {

    Categorie createCategorie(Categorie categorie);

    Categorie updateCategorie(Categorie categorie);

    void deleteCategorie(Categorie categorie);

    List<Categorie> getCategories();

    Categorie findOne(Long id);
}
