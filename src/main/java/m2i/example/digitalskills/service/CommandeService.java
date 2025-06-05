// src/main/java/m2i/example/digitalskills/service/CommandeService.java
package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Commande;
import m2i.example.digitalskills.model.LigneCommande;

import java.util.List;

public interface CommandeService {


    LigneCommande ajouterLigneCommande(Long commandeId, LigneCommande ligneCommande);

    List<Commande> getAllCommandes();

    Commande getCommandeById(Long id);

    Commande createCommande(Long clientId, String name, String email, String adresseLivraison);
}
