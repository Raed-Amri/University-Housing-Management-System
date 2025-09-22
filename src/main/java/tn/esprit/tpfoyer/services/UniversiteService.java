package tn.esprit.tpfoyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.models.Foyer;
import tn.esprit.tpfoyer.models.Universite;
import tn.esprit.tpfoyer.repositories.FoyerRepository;
import tn.esprit.tpfoyer.repositories.UniversiteRepository;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService{
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    private FoyerRepository foyerRepository;


    @Override
    public List<Universite> retrieveUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite updateUniversite(Universite universite, long idUniversite) {
        Universite existingUniversite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("etudiant not found with id "+idUniversite));
        existingUniversite.setAdresse(universite.getAdresse());
        existingUniversite.setNomUniverse(universite.getNomUniverse());

        return universiteRepository.save(existingUniversite);
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {
        return universiteRepository.findById((idUniversite)).orElse(null);
    }

    @Override
    public void removeUniversite(long idUniversite) {
        Universite existingUniversite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("etudiant not found with id "+idUniversite));
        universiteRepository.delete(existingUniversite);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniverse) {

        Universite universite = universiteRepository.findByNomUniverse(nomUniverse);
        if (universite == null) {
            throw new RuntimeException("Université non trouvée avec le nom " + nomUniverse);
        }
        Foyer foyer = foyerRepository.findById(idFoyer)

                .orElseThrow(() -> new RuntimeException("Foyer non trouvé avec l'ID " + idFoyer));

        if (universite.getFoyer() != null) {
            universite.setFoyer(null);
        }
        if (foyer.getUniversite() != null) {
            foyer.setUniversite(null);
        }
        universite.setFoyer(foyer);
       // foyer.setUniversite(universite);
       // foyerRepository.save(foyer);
        return universiteRepository.save(universite);
    }


    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {

        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée avec l'ID " + idUniversite));


        if (universite.getFoyer() != null) {

        //    Foyer foyer = universite.getFoyer();
            universite.setFoyer(null);
           // foyer.setUniversite(null);
          //  foyerRepository.save(foyer);
        }

        return universiteRepository.save(universite);
    }



}
