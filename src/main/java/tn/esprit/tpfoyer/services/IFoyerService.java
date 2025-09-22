package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.models.Foyer;

import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer addFoyer (Foyer f);
    Foyer updateFoyer (Foyer f,long idFoyer);
    Foyer retrieveFoyer (long idFoyer);
    void removeFoyer (long idFoyer);
    Foyer ajouterFoyerEtAffecterAUniversite (Foyer foyer, long idUniversite) ;

}
