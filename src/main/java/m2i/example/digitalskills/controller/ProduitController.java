// src/main/java/m2i/example/digitalskills/controller/ProduitController.java
package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Produit;
import m2i.example.digitalskills.repository.ProduitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitRepository produitRepository;

    public ProduitController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /**
     * GET /api/produits
     *   -> Renvoie la liste de tous les produits
     */
    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitRepository.findAll();
        return ResponseEntity.ok(produits);
    }

    /**
     * GET /api/produits/{id}
     *   -> Renvoie un produit par son ID, ou 404 si introuvable
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Optional<Produit> opt = produitRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(opt.get());
    }

    /**
     * POST /api/produits
     *   -> Crée un nouveau produit
     */
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        Produit saved = produitRepository.save(produit);
        return ResponseEntity.status(201).body(saved);
    }

    /**
     * PUT /api/produits/{id}
     *   -> Met à jour un produit existant
     */
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(
            @PathVariable Long id,
            @RequestBody Produit produit
    ) {
        Optional<Produit> opt = produitRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produit.setId(id);
        Produit updated = produitRepository.save(produit);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/produits/{id}
     *   -> Supprime un produit par son ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        if (!produitRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produitRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
