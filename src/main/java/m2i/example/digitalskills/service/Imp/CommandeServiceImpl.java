// src/main/java/m2i/example/digitalskills/service/Imp/CommandeServiceImpl.java
package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.Commande;
import m2i.example.digitalskills.model.LigneCommande;
import m2i.example.digitalskills.model.Panier;
import m2i.example.digitalskills.repository.CommandeRepository;
import m2i.example.digitalskills.repository.LigneCommandeRepository;
import m2i.example.digitalskills.service.CommandeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final ClientRepository clientRepository;
    private final PanierRepository panierRepository;
    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;

    public CommandeServiceImpl(ClientRepository clientRepository,
                               PanierRepository panierRepository,
                               CommandeRepository commandeRepository,
                               LigneCommandeRepository ligneCommandeRepository) {
        this.clientRepository = clientRepository;
        this.panierRepository = panierRepository;
        this.commandeRepository = commandeRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    @Override
    @Transactional
    public Commande createCommande(Long clientId, String name, String email, String adresseLivraison) {
        if (clientId == null) {
            throw new RuntimeException("ClientId manquant");
        }

        // 1) Récupérer le client
        Optional<Client> optClient = clientRepository.findById(clientId);
        if (optClient.isEmpty()) {
            throw new RuntimeException("Client introuvable");
        }
        Client client = optClient.get();

        // 2) Récupérer le panier courant
        Panier panier = panierRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Pas de panier pour ce client"));

        if (panier.getLignes().isEmpty()) {
            throw new RuntimeException("Le panier est vide");
        }

        // 3) Créer la commande
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setAdresseLivraison(adresseLivraison);
        commande.setMontantTotal(panier.getPrixTotal());
        commande.setStatus("EN_COURS");
        commande = commandeRepository.save(commande);

        // 4) Transformer chaque LignePanier en LigneCommande
        for (LignePanier lp : panier.getLignes()) {
            LigneCommande lc = new LigneCommande();
            lc.setProduit(lp.getProduit());
            lc.setPrixUnitaire(lp.getPrixUnitaire());
            lc.setQuantité(lp.getQuantite());
            lc.setCommande(commande);
            ligneCommandeRepository.save(lc);
        }

        // 5) Vider le panier (ou le supprimer, selon votre logique métier)
        panier.getLignes().clear();
        panier.setPrixTotal(java.math.BigDecimal.ZERO);
        panierRepository.save(panier);

        return commande;
    }
}
