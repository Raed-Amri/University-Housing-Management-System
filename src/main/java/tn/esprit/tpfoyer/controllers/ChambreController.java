package tn.esprit.tpfoyer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.models.Chambre;
import tn.esprit.tpfoyer.models.TypeChambre;
import tn.esprit.tpfoyer.services.IChambreService;

import java.util.List;

@RestController
@RequestMapping("chambres")
@AllArgsConstructor
public class ChambreController {
    //@Autowired
    IChambreService chambreService;
    @GetMapping("/get")
    public List<Chambre> retrieveAllChambres(){
        return chambreService.retrieveAllChambres();
    }
    @PostMapping("/add")
    public Chambre addChambre(@RequestBody Chambre c){
        return chambreService.addChambre(c);
    }
    @PutMapping("/put")
    public Chambre updateChambre(@RequestBody Chambre c){
        return chambreService.updateChambre(c);
    }
    @GetMapping("/get/{id}")
    public Chambre retrieveChambre(@PathVariable long id){
        return chambreService.retrieveChambre(id);
    }
    @GetMapping("/get/chambre_universite/{nomUniverse}")
    public List<Chambre> getChambresParNomUniversite(@PathVariable  String nomUniverse){
        return chambreService.getChambresParNomUniversite(nomUniverse);
    }
    @GetMapping("/get/idBloc_typeC/{idBloc}/{typeC}")
    public List<Chambre> getChambresParBlocEtType(@PathVariable long idBloc,@PathVariable TypeChambre typeC){
        return chambreService.getChambresParBlocEtType(idBloc,typeC);
    }

}
