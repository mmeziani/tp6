package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Produit;

import java.util.List;

public interface ProduitService {

    Produit createProduit(Produit produit);
    Produit updateProduit(Produit produit);

    void deleteProduit(Produit produit);

    List<Produit> getProduits();

    Produit findOne(Long id);

    List<Produit> getProduitsByCategorie(Long categorieId);
}
