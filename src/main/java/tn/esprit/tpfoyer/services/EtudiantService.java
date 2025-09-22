package tn.esprit.tpfoyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.models.Etudiant;
import tn.esprit.tpfoyer.repositories.EtudiantRepository;

import java.util.List;

@Service
public class EtudiantService implements IEtudiantService{
    @Autowired
    EtudiantRepository etudiantRepository;


    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> retrieveEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant retrieveEtudiant(long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse(null);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant,long idEtudiant) {
        Etudiant existingEtudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new RuntimeException("etudiant not found with id "+idEtudiant));
        existingEtudiant.setNomEl(etudiant.getNomEl());
        existingEtudiant.setPrenomEt(etudiant.getPrenomEt());
        existingEtudiant.setCin(etudiant.getCin());
        existingEtudiant.setEcole(etudiant.getEcole());
        existingEtudiant.setDateNaissance(etudiant.getDateNaissance());
        return etudiantRepository.save(existingEtudiant);
    }

    @Override
    public void removeEtudiant(long idEtudiant) {
        Etudiant existingEtudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new RuntimeException("etudiant not found with id "+idEtudiant));
        etudiantRepository.delete(existingEtudiant);

    }
}
