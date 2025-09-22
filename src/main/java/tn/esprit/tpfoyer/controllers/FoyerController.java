package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.models.Foyer;
import tn.esprit.tpfoyer.services.IFoyerService;

import java.util.List;

@RestController
@RequestMapping("foyers")
public class FoyerController {
    @Autowired
    IFoyerService foyerService;
    @PostMapping("/add")
    public Foyer addFoyer(@RequestBody Foyer foyer) {

        return foyerService.addFoyer(foyer);
    }
    @GetMapping("/get")
    public List<Foyer> retrieveFoyer(){
        return foyerService.retrieveAllFoyers();
    }
    @GetMapping("/get/{id}")
    public Foyer retrieveFoyer(@PathVariable long id ){
        return foyerService.retrieveFoyer(id);
    }
    @PutMapping("/put/{id}")
    public Foyer updateFoyer(@PathVariable long id,@RequestBody Foyer foyer){
        return foyerService.updateFoyer(foyer,id);
    }
    @DeleteMapping("/delete/{id}")
    public void  removeFoyer(@PathVariable long id){
        foyerService.removeFoyer(id);
    }

    @PostMapping("/ajouterFoyerEtAffecterAUniversite/{idUniverse}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer,  @PathVariable long idUniverse){
       return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniverse);
    }
}
