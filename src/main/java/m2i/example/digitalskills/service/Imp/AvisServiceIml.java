package m2i.example.digitalskills.service.Imp;

import m2i.example.digitalskills.model.Avis;
import m2i.example.digitalskills.repository.AvisRepository;
import m2i.example.digitalskills.service.AvisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisServiceIml implements AvisService {

    private AvisRepository avisRepository;

    public AvisServiceIml(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    @Override
    public Avis createAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    @Override
    public Avis updateAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    @Override
    public void deleteAvis(Avis avis) {
        avisRepository.delete(avis);
    }

    @Override
    public List<Avis> getAvis() {
        return avisRepository.findAll();
    }

    @Override
    public Avis findOne(Long id) {
        Optional<Avis> avis1 = avisRepository.findById(id);
        return avis1.orElseThrow(() -> new RuntimeException("Avis n'existe pas"));
    }
}
