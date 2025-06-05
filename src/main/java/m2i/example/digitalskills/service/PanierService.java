// src/main/java/m2i/example/digitalskills/service/PanierService.java
package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Panier;
import m2i.example.digitalskills.model.LignePanier;

import java.util.List;

public interface PanierService {

    Panier createPanier(Panier panier);

    Panier updatePanier(Panier panier);

    void deletePanier(Panier panier);

    List<Panier> getPaniers();

    Panier findOne(Long id);

    /**
     * Récupère ou crée un panier pour le client donné.
     */
    Panier getOrCreatePanierForClient(Long clientId);

    /**
     * Ajoute une ligne (LignePanier) au panier du client.
     */
    Panier ajouterLigneAuPanier(Long clientId, LignePanier lignePanier);
}
