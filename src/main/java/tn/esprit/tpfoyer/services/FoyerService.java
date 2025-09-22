package tn.esprit.tpfoyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.models.Etudiant;
import tn.esprit.tpfoyer.models.Foyer;
import tn.esprit.tpfoyer.models.Universite;
import tn.esprit.tpfoyer.repositories.BlocRepository;
import tn.esprit.tpfoyer.repositories.FoyerRepository;
import tn.esprit.tpfoyer.repositories.UniversiteRepository;

import java.util.List;

@Service
public class FoyerService implements IFoyerService {
    @Autowired
    FoyerRepository foyerRepository;
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    private BlocRepository blocRepository;
    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f,long idFoyer) {
        Foyer existingFoyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("foyer not found with id "+idFoyer));
        existingFoyer.setNomFoyer(f.getNomFoyer());
        existingFoyer.setCapa(f.getCapa());

        return foyerRepository.save(existingFoyer);
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public void removeFoyer(long idFoyer) {
        Foyer existingFoyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("foyer not found with id "+idFoyer));
        foyerRepository.delete(existingFoyer);
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniverse) {
        Universite existingUniversity = universiteRepository.findById(idUniverse)
                .orElseThrow(() -> new RuntimeException("universite not found with id " + idUniverse));
        existingUniversity.setFoyer(foyer);
        //universiteRepository.save(existingUniversity);
        Foyer savedFoyer = foyerRepository.save(foyer);

        if (foyer.getBlocs() != null) {
            for (Bloc bloc : foyer.getBlocs()) {
                bloc.setFoyer(savedFoyer);
                blocRepository.save(bloc);
            }
        }
        return savedFoyer;
    }
}
