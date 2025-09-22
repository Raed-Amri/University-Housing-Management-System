package tn.esprit.tpfoyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.models.Chambre;
import tn.esprit.tpfoyer.models.TypeChambre;
import tn.esprit.tpfoyer.models.Universite;
import tn.esprit.tpfoyer.repositories.BlocRepository;
import tn.esprit.tpfoyer.repositories.ChambreRepository;
import tn.esprit.tpfoyer.repositories.UniversiteRepository;

import java.util.List;

@Service
public class ChambreService implements IChambreService {
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    BlocRepository blocRepository;
    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        Chambre existingChambre = chambreRepository.findById(c.getIdChambre())
                .orElseThrow(() -> new RuntimeException("chambre not found with id: " + c.getIdChambre()));


        existingChambre.setNumchambre(c.getNumchambre());
        existingChambre.setTypeC(c.getTypeC());
        existingChambre.setBloc(c.getBloc());
        existingChambre.setReservations(c.getReservations());

        return chambreRepository.save(existingChambre);
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniverse) {
       return chambreRepository.findByBloc_Foyer_Universite_nomUniverse(nomUniverse);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {

        return chambreRepository.findChambresByBlocAndType(idBloc, typeC);
    }
}

