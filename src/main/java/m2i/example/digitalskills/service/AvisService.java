package m2i.example.digitalskills.service;

import m2i.example.digitalskills.model.Avis;

import java.util.List;

public interface AvisService {

    Avis createAvis(Avis avis);

    Avis updateAvis(Avis avis);

    void deleteAvis(Avis avis);

    List<Avis> getAvis();

    Avis findOne(Long id);
}
