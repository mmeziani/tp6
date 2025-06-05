package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.LigneCommande;
import m2i.example.digitalskills.service.LigneCommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lignecommandes")
public class LigneCommandeController {
    private LigneCommandeService ligneCommandeService;

    public LigneCommandeController(LigneCommandeService ligneCommandeService) {
        this.ligneCommandeService = ligneCommandeService;
    }

    @PostMapping
    public ResponseEntity<LigneCommande> createLigneCommande(@RequestBody LigneCommande ligneCommande){
        return new ResponseEntity<>(ligneCommandeService.createLigneCommande(ligneCommande), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LigneCommande> updateLigneCommande(@RequestBody LigneCommande ligneCommande){
        return new ResponseEntity<>(ligneCommandeService.updateLigneCommande(ligneCommande), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteLigneCommande(@RequestBody LigneCommande ligneCommande){
        ligneCommandeService.deleteLigneCommande(ligneCommande);
    }

    @GetMapping
    public List<LigneCommande> getLigneCommandes(){
        return ligneCommandeService.getLigneCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneCommande> findOne(@PathVariable Long id){
        return ResponseEntity.ok(ligneCommandeService.findOne(id));
    }


}
