package m2i.example.digitalskills.controller;

import m2i.example.digitalskills.model.Categorie;
import m2i.example.digitalskills.service.CategorieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie){
        return new ResponseEntity<>(categorieService.createCategorie(categorie), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Categorie> updateCategorie(@RequestBody Categorie categorie){
        return new ResponseEntity<>(categorieService.updateCategorie(categorie), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteCategorie(@RequestBody Categorie categorie){
        categorieService.deleteCategorie(categorie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> findOne(@PathVariable Long id){
        return ResponseEntity.ok(categorieService.findOne(id));
    }

    @GetMapping
    public List<Categorie> getCategories(){
        return categorieService.getCategories();
    }
}
