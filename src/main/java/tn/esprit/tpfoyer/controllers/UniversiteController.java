package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.models.Universite;
import tn.esprit.tpfoyer.services.IChambreService;
import tn.esprit.tpfoyer.services.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("universites")
public class UniversiteController {
    @Autowired
    IUniversiteService universiteService;
    @PostMapping("/add")
    public Universite addUniversite(@RequestBody Universite universite) {

        return universiteService.addUniversite(universite);
    }
    @GetMapping("/get")
    public List<Universite> retrieveUniversite(){
        return universiteService.retrieveUniversites();
    }
    @GetMapping("/get/{id}")
    public Universite retrieveUniversite(@PathVariable long id ){
        return universiteService.retrieveUniversite(id);
    }
    @PutMapping("/put/{id}")
    public Universite updateUniversite(@PathVariable long id,@RequestBody Universite universite){
        return universiteService.updateUniversite(universite,id);
    }
    @DeleteMapping("/delete/{id}")
    public void  removeUniversite(@PathVariable long id){
        universiteService.removeUniversite(id);
    }

    @PostMapping ("/affecterfoyer/{idFoyer}/{nomUniverse}")
    public Universite affecterFoyerAUniversite(@PathVariable("idFoyer") long idFoyer, @PathVariable("nomUniverse") String nomUniverse) {

            return universiteService.affecterFoyerAUniversite(idFoyer,nomUniverse);
    }
    @PutMapping("/desaffecterfoyer/{idUniverse}")
    public Universite desaffecterFoyerAUniversite(@PathVariable("idUniverse") long idUniverse){
        return universiteService.desaffecterFoyerAUniversite(idUniverse);
    }


}
