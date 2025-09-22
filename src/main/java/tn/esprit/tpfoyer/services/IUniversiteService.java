package tn.esprit.tpfoyer.services;


import tn.esprit.tpfoyer.models.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveUniversites();
    Universite addUniversite (Universite universite);
    Universite updateUniversite (Universite universite,long idUniversite);
    Universite retrieveUniversite (long idUniversite);
    void removeUniversite (long idUniversite);
    Universite affecterFoyerAUniversite (long idFoyer, String nomUniverse) ;
    Universite desaffecterFoyerAUniversite (long idUniverse) ;
}
