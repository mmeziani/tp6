package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.Produit;
import m2i.example.digitalskills.repository.ProduitRepository;
import m2i.example.digitalskills.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImp implements ProduitService {

    private ProduitRepository produitRepository;

    public ProduitServiceImp(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public void deleteProduit(Produit produit) {
        produitRepository.delete(produit);
    }

    @Override
    public List<Produit> getProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit findOne(Long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        return produit.orElseThrow(() -> new RuntimeException("Produit n'existe pas"));
    }

    @Override
    public List<Produit> getProduitsByCategorie(Long categorieId){
        return produitRepository.findByCategorieId(categorieId);
    }
}
