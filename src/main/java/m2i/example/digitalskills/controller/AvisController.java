package m2i.example.digitalskills.controller;


import m2i.example.digitalskills.model.Avis;
import m2i.example.digitalskills.service.AvisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    private AvisService avisService;

    public AvisController(AvisService avisService) {
        this.avisService = avisService;
    }

    @PostMapping
    public ResponseEntity<Avis> createAvis(@RequestBody Avis avis){
        return new ResponseEntity<>(avisService.createAvis(avis), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Avis> updateAvis(@RequestBody Avis avis){
        return new ResponseEntity<>(avisService.updateAvis(avis), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteAvis(@RequestBody Avis avis){
        avisService.deleteAvis(avis);
    }

    @GetMapping
    public List<Avis> getAvis(){
        return avisService.getAvis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avis> findOne(@PathVariable Long id){
        return ResponseEntity.ok(avisService.findOne(id));
    }
}
