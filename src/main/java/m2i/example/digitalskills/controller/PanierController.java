// src/main/java/m2i/example/digitalskills/controller/PanierController.java
package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Panier;
import m2i.example.digitalskills.model.LignePanier;
import m2i.example.digitalskills.service.PanierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @PostMapping
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier) {
        return new ResponseEntity<>(panierService.createPanier(panier), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Panier> updatePanier(@RequestBody Panier panier) {
        return new ResponseEntity<>(panierService.updatePanier(panier), HttpStatus.OK);
    }

    @DeleteMapping
    public void deletePanier(@RequestBody Panier panier) {
        panierService.deletePanier(panier);
    }

    @GetMapping
    public ResponseEntity<?> getPaniers() {
        return ResponseEntity.ok(panierService.getPaniers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panier> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(panierService.findOne(id));
    }

    /**
     * GET /api/paniers/current
     *
     * - Lit le cookie "clientId" si présent :
     *    • s’il existe : getOrCreatePanierForClient(clientId)
     *    • sinon      : createPanierForNewClient(), ajoute Set-Cookie "clientId"
     */
    @GetMapping("/current")
    public ResponseEntity<Panier> getOrCreatePanier(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Long clientId = null;

        // 1) Lire le cookie "clientId"
        if (request.getCookies() != null) {
            Optional<Cookie> cookieOpt = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("clientId"))
                    .findFirst();
            if (cookieOpt.isPresent()) {
                try {
                    clientId = Long.parseLong(cookieOpt.get().getValue());
                } catch (NumberFormatException e) {
                    clientId = null;
                }
            }
        }

        Panier panier;
        if (clientId == null) {
            // 2) Pas de cookie : créer nouveau client temp + panier
            panier = panierService.createPanierForNewClient();
            Long nouveauClientId = panier.getClient().getId();
            ResponseCookie cookie = ResponseCookie.from("clientId", String.valueOf(nouveauClientId))
                    .path("/")
                    .httpOnly(false)
                    .maxAge(7L * 24 * 60 * 60) // 7 jours
                    .build();
            response.addHeader("Set-Cookie", cookie.toString());
        } else {
            // 3) Cookie existant : récupérer ou créer le panier
            panier = panierService.getOrCreatePanierForClient(clientId);
        }

        return ResponseEntity.ok(panier);
    }

    /**
     * POST /api/paniers/{panierId}/lignes
     * Permet d’ajouter une ligne dans le panier, puis renvoie le panier mis à jour.
     */
    @PostMapping("/{panierId}/lignes")
    public ResponseEntity<Panier> ajouterLigneAuPanier(
            @PathVariable("panierId") Long panierId,
            @RequestBody LignePanier ligneDto
    ) {
        Panier panierMisAJour = panierService.ajouterLigneAuPanier(panierId, ligneDto);
        return new ResponseEntity<>(panierMisAJour, HttpStatus.CREATED);
    }
}
