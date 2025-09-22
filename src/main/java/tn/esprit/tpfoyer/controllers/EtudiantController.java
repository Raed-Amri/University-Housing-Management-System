package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.models.Etudiant;
import tn.esprit.tpfoyer.services.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("etudiants")
public class EtudiantController {
    @Autowired
    IEtudiantService etudiantService;
    @PostMapping("/add")
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {

        return etudiantService.addEtudiant(etudiant);
    }
    @GetMapping("/get")
    public List<Etudiant> retrieveEtudiants(){
        return etudiantService.retrieveEtudiants();
    }
    @GetMapping("/get/{id}")
    public Etudiant retrieveEtudiant(@PathVariable long id ){
        return etudiantService.retrieveEtudiant(id);
    }
    @PutMapping("/put/{id}")
    public Etudiant updateEtudiant(@PathVariable long id,@RequestBody Etudiant etudiant){
        return etudiantService.updateEtudiant(etudiant,id);
    }
    @DeleteMapping("/delete/{id}")
    public void removeEtudiant(@PathVariable long id){
         etudiantService.removeEtudiant(id);
    }
}

