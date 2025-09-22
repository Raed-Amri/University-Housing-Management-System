package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.models.Etudiant;

import java.util.List;

public interface IEtudiantService {

    Etudiant addEtudiant(Etudiant etudiant);
    List<Etudiant> retrieveEtudiants();
    Etudiant retrieveEtudiant (long idEtudiant);
    Etudiant updateEtudiant (Etudiant etudiant,long idEtudiant);
    void removeEtudiant (long idEtudiant);


}
