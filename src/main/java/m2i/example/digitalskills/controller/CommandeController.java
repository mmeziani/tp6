// src/main/java/m2i/example/digitalskills/controller/CommandeController.java
package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Commande;
import m2i.example.digitalskills.model.LigneCommande;
import m2i.example.digitalskills.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    /**
     * POST /api/commandes
     * Body JSON attendu :
     * {
     *   "client": { "id": 5 },
     *   "adresseLivraison": "123 rue Exemple",
     *   "status": "EN_ATTENTE"
     * }
     */
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande saved = commandeService.createCommande(commande);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * POST /api/commandes/{commandeId}/lignes
     * Body JSON attendu :
     * {
     *   "produit": { "id": 2 },
     *   "prixUnitaire": 59.99,
     *   "quantite": 1
     * }
     */
    @PostMapping("/{commandeId}/lignes")
    public ResponseEntity<?> ajouterLigneCommande(
            @PathVariable("commandeId") Long commandeId,
            @RequestBody LigneCommande ligneCommande
    ) {
        Commande commande = commandeService.getCommandeById(commandeId);
        LigneCommande saved = commandeService.ajouterLigneCommande(commandeId, ligneCommande);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
