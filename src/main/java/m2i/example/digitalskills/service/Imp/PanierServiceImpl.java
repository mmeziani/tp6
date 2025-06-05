// src/main/java/m2i/example/digitalskills/service/Imp/PanierServiceImpl.java
package m2i.example.digitalskills.service.Imp;

import jakarta.transaction.Transactional;
import m2i.example.digitalskills.model.Client;
import m2i.example.digitalskills.model.LignePanier;
import m2i.example.digitalskills.model.Panier;
import m2i.example.digitalskills.repository.ClientRepository;
import m2i.example.digitalskills.repository.LignePanierRepository;
import m2i.example.digitalskills.repository.PanierRepository;
import m2i.example.digitalskills.service.PanierService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {  private final PanierRepository panierRepository;
    private final ClientRepository clientRepository;

    public PanierServiceImpl(PanierRepository panierRepository,
                             ClientRepository clientRepository) {
        this.panierRepository = panierRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Panier createPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public Panier updatePanier(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public void deletePanier(Panier panier) {
        panierRepository.delete(panier);
    }

    @Override
    public List<Panier> getPaniers() {
        return panierRepository.findAll();
    }

    @Override
    public Panier findOne(Long id) {
        Optional<Panier> opt = panierRepository.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Panier introuvable"));
    }

    @Override
    @Transactional
    public Panier getOrCreatePanierForClient(Long clientId) {
        if (clientId == null) {
            throw new RuntimeException("ClientId manquant");
        }

        // 1) Récupérer le client
        Optional<Client> optClient = clientRepository.findById(clientId);
        if (optClient.isEmpty()) {
            throw new RuntimeException("Client introuvable pour ID " + clientId);
        }
        Client client = optClient.get();

        // 2) Chercher un panier existant pour ce client (non encore vidé/commandé)
        Optional<Panier> optPanier = panierRepository.findByClientId(clientId);
        if (optPanier.isPresent()) {
            return optPanier.get();
        }

        // 3) Si aucun panier existant, on en crée un nouveau
        Panier nouveauPanier = new Panier();
        nouveauPanier.setClient(client);
        return panierRepository.save(nouveauPanier);
    }

    @Override
    @Transactional
    public Panier ajouterLigneAuPanier(Long clientId, LignePanier lignePanier) {
        if (clientId == null) {
            throw new RuntimeException("ClientId manquant");
        }

        if (lignePanier == null || lignePanier.getProduit() == null) {
            throw new RuntimeException("LignePanier ou Produit manquant");
        }

        // 1) Récupérer (ou créer) le panier du client
        Panier panier = getOrCreatePanierForClient(clientId);

        // 2) Calculer et remplir les champs de LignePanier (prixUnitaire, produit, quantite…)
        //    On suppose que le front a déjà rempli : prixUnitaire, quantite et produit.id
        //    Il faut que 'lignePanier.getProduit().getId()' soit non null.
        //    En base, la relation produit est gérée automatiquement par JPA grâce au JSON.
        panier.ajouterLigne(lignePanier);

        // 3) Sauvegarder le panier (et, par cascade, la nouvelle ligne)
        return panierRepository.save(panier);
    }
}
