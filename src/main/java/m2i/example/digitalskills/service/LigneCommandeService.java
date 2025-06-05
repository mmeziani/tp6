package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.LigneCommande;

import java.util.List;

public interface LigneCommandeService {

    LigneCommande createLigneCommande(LigneCommande ligneCommande);

    LigneCommande updateLigneCommande(LigneCommande ligneCommande);

    void deleteLigneCommande(LigneCommande ligneCommande);

    List<LigneCommande> getLigneCommandes();

    LigneCommande findOne(Long id);
}
