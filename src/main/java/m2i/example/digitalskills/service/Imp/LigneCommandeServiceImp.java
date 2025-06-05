package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.LigneCommande;
import m2i.example.digitalskills.repository.LigneCommandeRepository;
import m2i.example.digitalskills.service.LigneCommandeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneCommandeServiceImp implements LigneCommandeService {

    private LigneCommandeRepository ligneCommandeRepository;

    public LigneCommandeServiceImp(LigneCommandeRepository ligneCommandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    @Override
    public LigneCommande createLigneCommande(LigneCommande ligneCommande) {
        return ligneCommandeRepository.save(ligneCommande);
    }

    @Override
    public LigneCommande updateLigneCommande(LigneCommande ligneCommande) {
        return ligneCommandeRepository.save(ligneCommande);
    }

    @Override
    public void deleteLigneCommande(LigneCommande ligneCommande) {
        ligneCommandeRepository.delete(ligneCommande);
    }

    @Override
    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandeRepository.findAll();
    }

    @Override
    public LigneCommande findOne(Long id) {
        Optional<LigneCommande> ligneCommande = ligneCommandeRepository.findById(id);
        return ligneCommande.orElseThrow(() -> new RuntimeException("Ligne de commande n'existe pas"));
    }
}
